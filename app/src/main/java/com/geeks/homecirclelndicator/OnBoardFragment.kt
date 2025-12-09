package com.geeks.homecirclelndicator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.annotation.R
import androidx.navigation.fragment.findNavController
import com.geeks.homecirclelndicator.databinding.FragmentOnBoardBinding
import java.nio.file.Files.find


class OnBoardFragment : Fragment() {
     private lateinit var binding: FragmentOnBoardBinding
     private lateinit var adapter: OnBoardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OnBoardAdapter(getOnBoardList(),::navigateToMain, ::onSkip)
        binding.vpOnBoard.adapter = adapter
        binding.circleIndicator.setViewPager(binding.vpOnBoard)
        adapter.registerAdapterDataObserver(binding.circleIndicator.adapterDataObserver)
    }

    private fun navigateToMain(){
      findNavController().navigate(OnBoardFragmentDirections.actionOnBoardFragmentToSecondActivity())
    }

    private fun onSkip(endPosition: Int){
       binding.vpOnBoard.currentItem = endPosition
    }

    private fun getOnBoardList(): List<OnBoardModel>{
        return listOf(OnBoardModel("Удобство", "Удобство\n" +
                "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно.", com.geeks.homecirclelndicator.R.drawable.gif_onboard_1),
            OnBoardModel("Организация", "Организация\n" +
                    "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время.", com.geeks.homecirclelndicator.R.drawable.gif_onboard_2),
            OnBoardModel("Синхронизация", "Синхронизация\n" +
                    "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте.", com.geeks.homecirclelndicator.R.drawable.gif_onboard_3))
    }
}