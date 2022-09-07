package com.gauravbora.javatrivia

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.Person.fromBundle
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

import com.gauravbora.javatrivia.R
import com.gauravbora.javatrivia.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false
        )

        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }


        val args = GameWonFragmentArgs.fromBundle(requireArguments())
//        Toast.makeText(context,"NumCorrect: ${args.numCorrect }, NumQuestions: ${args.numQuestions}",
//            Toast.LENGTH_LONG).show()

        setHasOptionsMenu(true)
        return binding.root
    }

    //share menu
    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
       return ShareCompat.IntentBuilder
           .from(requireActivity())
           .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
           .setType("text/plain").intent
    }

    private  fun shareSuccess(){
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share-> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}



