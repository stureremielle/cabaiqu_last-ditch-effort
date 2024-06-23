package com.surendramaran.yolov8tflite

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class SettingsFragment : DialogFragment() {

    private lateinit var seekBarIoUThreshold: SeekBar
    private lateinit var textIoUThreshold: TextView
    private lateinit var seekBarConfidenceThreshold: SeekBar
    private lateinit var textConfidenceThreshold: TextView
    private var listener: SettingsFragmentListener? = null

    interface SettingsFragmentListener {
        fun onIoUThresholdChanged(threshold: Float)
        fun onConfidenceThresholdChanged(threshold: Float)
    }

    fun setListener(listener: SettingsFragmentListener) {
        this.listener = listener
    }

    companion object {
        private const val ARG_IOU_THRESHOLD = "iou_threshold"
        private const val ARG_CONFIDENCE_THRESHOLD = "confidence_threshold"

        fun newInstance(iouThreshold: Float, confidenceThreshold: Float): SettingsFragment {
            val fragment = SettingsFragment()
            val args = Bundle()
            args.putFloat(ARG_IOU_THRESHOLD, iouThreshold)
            args.putFloat(ARG_CONFIDENCE_THRESHOLD, confidenceThreshold)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_settings, null)

        seekBarIoUThreshold = view.findViewById(R.id.seekbar_iou_threshold)
        textIoUThreshold = view.findViewById(R.id.text_iou_threshold)
        seekBarConfidenceThreshold = view.findViewById(R.id.seekbar_confidence_threshold)
        textConfidenceThreshold = view.findViewById(R.id.text_confidence_threshold)

        setupSeekBarListeners()
        setInitialValues()

        dialog.setContentView(view)

        // Apply the custom style and set the rounded background drawable
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        dialog.window?.setDimAmount(0.7f) // Optional: Adjust the dim amount behind the dialog

        return dialog
    }

    private fun setInitialValues() {
        val args = arguments
        if (args != null) {
            val currentIoUThreshold = args.getFloat(ARG_IOU_THRESHOLD, 0.5f)
            val currentConfidenceThreshold = args.getFloat(ARG_CONFIDENCE_THRESHOLD, 0.5f)

            seekBarIoUThreshold.progress = (currentIoUThreshold * 100).toInt()
            textIoUThreshold.text = String.format("%.2f", currentIoUThreshold)

            seekBarConfidenceThreshold.progress = (currentConfidenceThreshold * 100).toInt()
            textConfidenceThreshold.text = String.format("%.2f", currentConfidenceThreshold)
        }
    }

    private fun setupSeekBarListeners() {
        seekBarIoUThreshold.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val iouThreshold = progress / 100.0f
                textIoUThreshold.text = String.format("%.2f", iouThreshold)
                listener?.onIoUThresholdChanged(iouThreshold)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarConfidenceThreshold.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val confidenceThreshold = progress / 100.0f
                textConfidenceThreshold.text = String.format("%.2f", confidenceThreshold)
                listener?.onConfidenceThresholdChanged(confidenceThreshold)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}