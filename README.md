# DownloadManager

Design a DownloadManager API that achieves the following - 

- Start/Enqueue a file download.
- Persists the download requests across app opens.
- Communicate to any subscriber about the states of a download (onProgress, onCancel, onStart, onError, etc.)
- Can return status of the download with a given download Id.
- Supports proper download request canceling.

Android has an API called the [DownloadManager.](https://developer.android.com/reference/android/app/DownloadManager) If it weren’t there and you had to design a DownloadManager, how would you go about it?


### 🍨 Brownie Points for Cases

1. Using a local broadcast receiver to deliver download complete.
2. If the files are very large but you dont want them to be visible to other apps.
3. Internally retries in cases of network failure.
4. More edge cases you can think of and handle, the better.

### 📔 Guidelines for Submission

- Fork this repository when you get access to it. 
- This Repository already contains a skeleton of a DownloadManager implementation. 
- Code the incomplete parts of the API, while on call with a dev from Supershare.
- Commit and raise PR onto the Repo. 
- You will be judged based on code quality & cleanliness, reasoning behind architectural choices and aproach to solve bit sized problems.
  The codebase not being able to build **will not be a dealbreaker**.

---

**Good Luck! 👍🏼**
