package com.example.taghive.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taghive.R
import com.example.taghive.databinding.FragmentAllCryptoBinding
import com.example.taghive.databinding.FragmentDetailedCryptoBinding
import com.example.taghive.domain.model.CryptoDataClassItem
import com.kaopiz.kprogresshud.KProgressHUD
import org.koin.android.ext.android.inject


class DetailedCryptoFragment : Fragment() {
    private lateinit var binding: FragmentDetailedCryptoBinding
    private val cryptoViewModel: CryptoViewModel by inject()
    lateinit var progressDialog: KProgressHUD
    private lateinit var cryptoAdapter: CryptoListAdapter
    private var cryptoName = ""
    private var baseAsset = ""
    private var quoteAsset = ""
    private var openPrice = ""
    private var lowPrice = ""
    private var highPrice = ""
    private var lastPrice = ""
    private var volume = ""
    private var bidPrice = ""
    private var askPrice = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        progressDialog = KProgressHUD.create(requireActivity())
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(getString(R.string.loading))
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
        binding = FragmentDetailedCryptoBinding.inflate(layoutInflater, container, false)
        binding.toolbar.imgBack.visibility = View.VISIBLE
        binding.toolbar.txtCenter.visibility = View.VISIBLE
        cryptoName = arguments?.getString("name").toString()
        baseAsset = arguments?.getString("baseAsset").toString()
        quoteAsset = arguments?.getString("quoteAsset").toString()
        openPrice = arguments?.getString("openPrice").toString()
        lowPrice = arguments?.getString("lowPrice").toString()
        highPrice = arguments?.getString("highPrice").toString()
        lastPrice = arguments?.getString("lastPrice").toString()
        volume = arguments?.getString("volume").toString()
        bidPrice = arguments?.getString("bidPrice").toString()
        askPrice = arguments?.getString("askPrice").toString()

        binding.toolbar.txtCenter.text = cryptoName
        binding.toolbar.imgBack.setOnClickListener {
            requireActivity().run {
                onBackPressed()
            }
        }


        binding.txtCryptoSymbol.text = cryptoName
        binding.txtBaseAsset.text = baseAsset
        binding.txtQuoteAsset.text = quoteAsset
        binding.txtOpenPrice.text = openPrice
        binding.txtLowPrice.text = lowPrice
        binding.txtHighPrice.text = highPrice
        binding.txtLastPrice.text = lastPrice
        binding.txtVolume.text = volume
        binding.txtBidPrice.text = bidPrice
        binding.txtAskPrice.text = askPrice

        cryptoViewModel.getloading().observe(viewLifecycleOwner) {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }
        return binding.root
    }


}