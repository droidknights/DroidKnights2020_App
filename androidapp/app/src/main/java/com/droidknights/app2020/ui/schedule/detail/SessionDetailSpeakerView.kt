package com.droidknights.app2020.ui.schedule.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.droidknights.app2020.R
import com.droidknights.app2020.data.Speaker
import com.droidknights.app2020.databinding.ViewSessionDetailSpeakerBinding

class SessionDetailSpeakerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private lateinit var binding: ViewSessionDetailSpeakerBinding

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
        if (speakerProfileImages.isNotEmpty()) {
            val profileSize =
                context.resources.getDimensionPixelSize(R.dimen.session_detail_speaker_profile_size)

            Glide.with(this)
                .load(speakerProfileImages[0])
                .apply(
                    RequestOptions()
                        .override(profileSize)
                        .placeholder(R.drawable.img_droid_space)
                        .error(R.drawable.img_droid_space)
                        .circleCrop()
                )
                .into(binding.ivSpeakerImage)
        }

        // TODO : 프로필 이미지 여러개 표시
    }

    private fun fetchSpeakersBelongAndName(speakers: List<Speaker>) {
        binding.tvSpeakerName.text = speakers.joinToString(" · ") { "${it.belong ?: ""} ${it.name}" }
    }
}