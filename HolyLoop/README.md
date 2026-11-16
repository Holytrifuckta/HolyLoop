# HolyLoop — Android prototype 0.1

This is a first-stage **source-code prototype**, not a completed ChatGPT controller.

## Current features
- Native Android launcher with a microphone transcription test
- Opens the installed ChatGPT application
- Optional accessibility-service declaration restricted to the ChatGPT package, currently intentionally inactive

## Not implemented yet
- Automatic sending, reading ChatGPT replies, floating overlay, ElevenLabs integration, multi-voice playback

## Phone-only build (GitHub)
1. Create a private GitHub repository named HolyLoop.
2. Upload the **contents** of this ZIP to the repository, keeping the `.github/workflows/android.yml` directory.
3. In GitHub, open **Actions → Build HolyLoop APK → Run workflow** (or push to main).
4. After the workflow completes, open its artifact `HolyLoop-debug-apk`, download and extract the ZIP, and install `app-debug.apk` on your Android phone. Android may require allowing installations from your browser/file manager.

Do not enter API keys into the repository or GitHub issue comments. This project does not currently use ElevenLabs or transmit your ChatGPT content.

The GitHub Actions build has not been executed or verified in this environment; if it fails, share the error log for correction.
