class FcmTokenService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        //check message contains data payload
        remoteMessage?.data?.isNotEmpty()?.let {
            //prepare some action like put data into variables
        }

        //check message contains notification payload
        remoteMessage?.notification?.let {
            //prepare some action like show notification
            showNotification(it)
        }
    }

    override fun onDeletedMessages() {
        //do something like server sync for deleted and not received message
    }

    override fun onNewToken(token: String?) {
        //send token to server to able to get messages
    }

    private fun showNotification(notification: RemoteMessage.Notification) {
        //create action on tap
        val intent = Intent(this, NotificationActionActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent, 0)

        //build notification
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(notification.title)
            .setContentText(notification.body)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        //show notification
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
    }

    fun fetchCurrentToken() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            //get token and do something
            val token = task.result?.token
        })
    }
}