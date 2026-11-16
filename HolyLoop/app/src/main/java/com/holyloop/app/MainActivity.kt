package com.holyloop.app

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
 private lateinit var status: TextView
 private val speechRequest = 42
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(32,48,32,32) }
  val title = TextView(this).apply { text = "HOLYLOOP · Prototype 0.1"; textSize = 24f }
  status = TextView(this).apply { text = "Voice dictation test. No messages are sent automatically yet."; textSize = 17f; setPadding(0,24,0,24) }
  val listen = Button(this).apply { text = "🎤 Test microphone"; setOnClickListener { startSpeech() } }
  val chat = Button(this).apply { text = "Open ChatGPT"; setOnClickListener {
   val intent = packageManager.getLaunchIntentForPackage("com.openai.chatgpt")
   if (intent != null) startActivity(intent) else status.text = "ChatGPT app not found."
  } }
  val access = Button(this).apply { text = "Accessibility settings (optional)"; setOnClickListener { startActivity(Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS)) } }
  layout.addView(title); layout.addView(status); layout.addView(listen); layout.addView(chat); layout.addView(access)
  setContentView(layout)
 }
 private fun startSpeech() {
  if (android.os.Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
   requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), 7); return
  }
  try {
   val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to HolyLoop")
   }
   startActivityForResult(intent, speechRequest)
  } catch (e: Exception) { status.text = "Speech recognition unavailable: ${e.message}" }
 }
 override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
  super.onRequestPermissionsResult(requestCode, permissions, grantResults)
  if (requestCode == 7 && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) startSpeech()
 }
 @Deprecated("Legacy Activity callback for compatibility")
 override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
  super.onActivityResult(requestCode, resultCode, data)
  if (requestCode == speechRequest) status.text = if (resultCode == RESULT_OK) data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull() ?: "No speech detected" else "Listening cancelled or unavailable"
 }
}
