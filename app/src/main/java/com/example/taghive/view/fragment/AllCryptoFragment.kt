package com.example.taghive.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taghive.R
import com.example.taghive.databinding.FragmentAllCryptoBinding
import com.example.taghive.domain.model.CryptoDataClassItem
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class AllCryptoFragment : Fragment(), ItemClickListener {
    private lateinit var binding: FragmentAllCryptoBinding
    private val cryptoViewModel: CryptoViewModel by inject()
    lateinit var progressDialog: KProgressHUD
    private lateinit var cryptoAdapter: CryptoListAdapter
    private val cryptoList = mutableListOf<CryptoDataClassItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //This is to show progressDialog until the response is fetched
        progressDialog = KProgressHUD.create(requireActivity())
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(getString(R.string.loading))
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
        binding = FragmentAllCryptoBinding.inflate(layoutInflater, container, false)


        //recyclerview initialization
        setRecyclerView()

        //calling the list of crypto api
        cryptoViewModel.listOfCryptos()

        //Observing data from server
        cryptoViewModel.getCryptoDatResponse().observe(viewLifecycleOwner) {
            cryptoList.clear()
            cryptoList.addAll(it)


            //Notifying the adapter here
            cryptoAdapter.notifyDataSetChanged()
        }

        //Showing and hiding loader
        cryptoViewModel.getloading().observe(viewLifecycleOwner) {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }
        return binding.root
    }


    //Layout manager setup
    private fun setRecyclerView() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.idTotalCrypto.layoutManager = layoutManager
        cryptoAdapter = CryptoListAdapter(cryptoList, this)
        binding.idTotalCrypto.adapter = cryptoAdapter
    }

    //passing the bundle to another fragment onclick
    override fun onCryptoListClicked(
        name: String,
        baseAsset: String,
        quoteAsset: String,
        openPrice: String,
        lowPrice: String,
        highPrice: String,
        lastPrice: String,
        volume: String,
        bidPrice: String,
        askPrice: String,
    ) {
        requireActivity().run {
            val fragment = DetailedCryptoFragment()
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("baseAsset", baseAsset)
            bundle.putString("quoteAsset", quoteAsset)
            bundle.putString("openPrice", openPrice)
            bundle.putString("lowPrice", lowPrice)
            bundle.putString("highPrice", highPrice)
            bundle.putString("lastPrice", lastPrice)
            bundle.putString("volume", volume)
            bundle.putString("bidPrice", bidPrice)
            bundle.putString("askPrice", askPrice)


            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(layout_container.id, fragment)
                .addToBackStack(DetailedCryptoFragment::class.java.simpleName)
                .commit()
        }
    }


}