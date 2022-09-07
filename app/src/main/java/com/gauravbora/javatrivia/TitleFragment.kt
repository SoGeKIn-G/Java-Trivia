package com.gauravbora.javatrivia

import android.app.ActionBar
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.gauravbora.javatrivia.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment

        val binding:FragmentTitleBinding=DataBindingUtil.inflate(
            inflater,R.layout.fragment_title,container,false
        )
        binding.playButton.setOnClickListener{v:View->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }


        setHasOptionsMenu(true)


        return binding.root
    }
//overflow menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,requireView().findNavController())
                ||super.onOptionsItemSelected(item)
    }
}

