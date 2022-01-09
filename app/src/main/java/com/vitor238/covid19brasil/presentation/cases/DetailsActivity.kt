package com.vitor238.covid19brasil.presentation.cases

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.databinding.ActivityDetailsBinding
import com.vitor238.covid19brasil.domain.model.BrazilianState
import com.vitor238.covid19brasil.utils.extension.setStateFlag

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setupTransition()
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        getIntentExtras()
    }

    private fun getIntentExtras() {
        intent.getParcelableExtra<BrazilianState>(BRAZILIAN_STATE)?.let { brazilianState ->
            binding.textBrazilianStateName.text = brazilianState.state
            binding.textNumberCases.text = brazilianState.cases
            binding.textNumberDeaths.text = brazilianState.deaths
            binding.textNumberSuspects.text = brazilianState.suspects
            binding.textNumberRefuses.text = brazilianState.refuses
            binding.textUpdateDate.text = brazilianState.datetime
            binding.imageFlag.setStateFlag(brazilianState.uf)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.title = getString(R.string.details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupTransition() {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        ViewCompat.setTransitionName(
            findViewById(android.R.id.content),
            "transitionActivity"
        )

        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        val materialTransform = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 450
            pathMotion = MaterialArcMotion()
        }
        window.sharedElementEnterTransition = materialTransform
    }


    companion object {
        private const val BRAZILIAN_STATE = "brazilianState"
        fun getIntent(context: Context, brazilianState: BrazilianState): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(BRAZILIAN_STATE, brazilianState)
            }
        }
    }

}