package com.example.simplequiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*

import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.simplequiz.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {

    // val args:GameWonFragmentArgs by navArgs()
    //var args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }
    var args: GameWonFragmentArgs? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameWonBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener {
            findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }
        Toast.makeText(
            context,
            "NumCorrect: ${args?.numCorrect} NumberOfQuestion:${args?.numQuestions}",
            Toast.LENGTH_SHORT
        ).show()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        //check the activity resolve
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            menu.findItem(R.id.share).setVisible(false)
        }
    }

    private fun getShareIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent
            .setType("text/plain")
            .putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.share_success_text, args?.numCorrect, args?.numQuestions)

            )
        return shareIntent

    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }


}