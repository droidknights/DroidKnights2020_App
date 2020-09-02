package com.droidknights.app2020.ui.schedule.detail

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.droidknights.app2020.R
import com.droidknights.app2020.data.Speaker
import com.droidknights.app2020.databinding.ViewSessionDetailSpeakerBinding
import com.droidknights.app2020.util.dp2px
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SessionDetailSpeakerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private lateinit var binding: ViewSessionDetailSpeakerBinding

    private val profileSize =
        context.resources.getDimensionPixelSize(R.dimen.session_detail_speaker_profile_size)
    private val profileMargin = 2.dp2px()
    private val profileOverlay = 10.dp2px()

    var speakers: List<Speaker> = emptyList()
        set(value) {
            field = value
            fetchSpeakersImage(value.map { it.profileImage })
            fetchSpeakersBelongAndName(value)
        }

    init {
        bindView()
    }

    private fun bindView() {
        binding = ViewSessionDetailSpeakerBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    private fun fetchSpeakersImage(speakerProfileImages: List<String>) {
        if (speakerProfileImages.isEmpty()) return

        if (speakerProfileImages.size == 1) {
            Glide.with(this)
                .load(speakerProfileImages[0])
                .apply(
                    RequestOptions()
                        .override(profileSize)
                        .error(R.drawable.img_droid_space)
                        .circleCrop()
                )
                .into(binding.ivSpeakerImage)
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            val bitmaps = getProfileBitmaps(speakerProfileImages)
            val result = mergeProfiles(bitmaps)
            binding.ivSpeakerImage.setImageBitmap(result)
        }
    }

    private fun fetchSpeakersBelongAndName(speakers: List<Speaker>) {
        binding.tvSpeakerName.text = speakers.joinToString(" · ") { "${it.belong ?: ""} ${it.name}" }
    }

    private suspend fun getProfileBitmaps(speakerProfileImages: List<String>): List<Bitmap> {
        return withContext(Dispatchers.IO) {
            val bitmaps = mutableListOf<Bitmap>()

            speakerProfileImages.map { url ->
                Glide.with(this@SessionDetailSpeakerView)
                    .asBitmap()
                    .load(url)
                    .apply(
                        RequestOptions()
                            .override(profileSize)
                            .error(R.drawable.img_droid_space)
                            .circleCrop()
                    )
                    .submit()
            }.forEachIndexed { i, future ->
                val resource = future.get()

                val isLastProfile = i == speakers.size - 1
                if (isLastProfile) {
                    bitmaps.add(resource)
                } else {
                    val mask = createCrescentMask(profileSize, profileSize)
                    bitmaps.add(getClippedResource(resource, mask))
                }
            }
            return@withContext bitmaps
        }
    }


    private fun mergeProfiles(bitmaps: List<Bitmap>): Bitmap {
        val totalWidth = (profileSize + profileMargin - profileOverlay) * (bitmaps.size - 1) + profileSize

        val result = Bitmap.createBitmap(
            totalWidth,
            profileSize,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(result)

        for (i in bitmaps.indices) {
            canvas.drawBitmap(
                bitmaps[i],
                0 + ((profileSize - profileOverlay) * i).toFloat(),
                0f,
                null
            )
        }
        return result
    }

    private fun createCrescentMask(width: Int, height: Int): Bitmap {
        val mask = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(mask)

        canvas.clipRect(0, 0, width, height)
        canvas.drawColor(Color.BLACK)

        val paint = Paint().apply {
            color = Color.TRANSPARENT
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }
        val widthHalf = width / 2
        val heightHalf = height / 2
        canvas.drawCircle(widthHalf.toFloat(), heightHalf.toFloat(), widthHalf.toFloat(), paint)

        paint.run {
            color = Color.BLACK
            xfermode = null
        }
        canvas.drawCircle(
            width + (widthHalf - (profileMargin + profileOverlay)).toFloat(),
            heightHalf.toFloat(),
            widthHalf.toFloat(),
            paint
        )

        return mask
    }

    private fun getClippedResource(resource: Bitmap, mask: Bitmap): Bitmap {
        val result = Bitmap.createBitmap(resource.width, resource.height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas()
        canvas.setBitmap(result)
        canvas.drawBitmap(resource, 0f, 0f, null)

        val paint = Paint().apply {
            isFilterBitmap = false
            xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        }

        canvas.drawBitmap(mask, 0f, 0f, paint)

        return result
    }
}