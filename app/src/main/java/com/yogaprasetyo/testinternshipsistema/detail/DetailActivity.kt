package com.yogaprasetyo.testinternshipsistema.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogaprasetyo.testinternshipsistema.R
import com.yogaprasetyo.testinternshipsistema.core.data.Resource
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsDetailItem
import com.yogaprasetyo.testinternshipsistema.core.utils.loadImage
import com.yogaprasetyo.testinternshipsistema.core.utils.merge
import com.yogaprasetyo.testinternshipsistema.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receiveId = intent?.getStringExtra(EXTRA_ID) ?: DEFAULT_ID
        detailViewModel.loadFood(receiveId).observe(this, Observer(::showResponse))
    }

    private fun showResponse(response: Resource<MealsDetailItem?>) {
        when (response) {
            is Resource.Loading -> showLoading()
            is Resource.Error -> showErrorInfo()
            is Resource.Success -> showFood(response.data)
        }
    }

    private fun showLoading(isShow: Boolean = true) {
        binding.pb.isVisible = isShow
        binding.apply {
            pb.isVisible = isShow
            titleInstructions.isVisible = !isShow
            titleIngredients.isVisible = !isShow
            fabOpenYt.isVisible = !isShow
        }
    }

    private fun showErrorInfo() {
        showLoading(false)
        val message = getString(R.string.general_error)
        showingInfo(message, true)
    }

    private fun showingInfo(message: String, isShow: Boolean) {
        binding.apply {
            tvInfo.text = message
            tvInfo.isVisible = isShow
            titleInstructions.isVisible = !isShow
            titleIngredients.isVisible = !isShow
            fabOpenYt.isVisible = !isShow
        }
    }

    private fun showFood(data: MealsDetailItem?) {
        showLoading(false)
        if (data == null) {
            val message = getString(R.string.detail_empty)
            showingInfo(message, true)
            return
        }

        data.apply {
            binding.ivThumbnail.loadImage(this@DetailActivity, strMealThumb)
            binding.tvName.text = strMeal
            binding.tvCategory.text = getString(R.string.detail_category, strCategory, strArea)
            binding.tvInstructions.text = strInstructions

            val ingredients = listOf(
                strMeasure1?.let { strIngredient1?.merge(it) },
                strMeasure2?.let { strIngredient2?.merge(it) },
                strMeasure3?.let { strIngredient3?.merge(it) },
                strMeasure4?.let { strIngredient4?.merge(it) },
                strMeasure5?.let { strIngredient5?.merge(it) },
                strMeasure6?.let { strIngredient6?.merge(it) },
                strMeasure7?.let { strIngredient7?.merge(it) },
                strMeasure8?.let { strIngredient8?.merge(it) },
                strMeasure9?.let { strIngredient9?.merge(it) },
                strMeasure10?.let { strIngredient10?.merge(it) }
            )

            binding.rvIngredients.apply {
                layoutManager = LinearLayoutManager(this@DetailActivity)
                adapter = DetailAdapter(ingredients)
            }

            binding.fabOpenYt.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(strYoutube)))
            }
        }
    }

    companion object {
        private const val DEFAULT_ID = "52772"
        const val EXTRA_ID = "extra_id"
    }
}