package com.example.myapplication.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNoteBinding
import com.example.myapplication.model.AppNote
import com.example.myapplication.screens.main.MainFragmentViewModel
import com.example.myapplication.utilits.APP_ACTIVITY
import com.example.myapplication.utilits.showToast

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: AppNote


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable("note") as AppNote
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
        mBinding.noteName.setText(mCurrentNote.name)
        mBinding.noteText.setText(mCurrentNote.text)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_delete -> {
                APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                mViewModel.delete(mCurrentNote){}
            }

            R.id.btn_save -> {
                mCurrentNote.name = mBinding.noteName.text.toString()
                mCurrentNote.text = mBinding.noteText.text.toString()
                if(mCurrentNote.name != ""){
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                    mViewModel.change(mCurrentNote){}
                } else{
                    showToast(getString(R.string.toast_write_name))
                }
            }
            R.id.btn_go_to_main_from_note -> {
                APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}