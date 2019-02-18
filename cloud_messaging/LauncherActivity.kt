class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        //check are some keys from notification exists 
        if(intent?.extras.containsKey("key1")) {
            //do some action depends on keys value like launch another activity
        }

        //subscribe or unsubscribe to topic in some conditions
    }

    private fun subscribeToTopic() {
	    FirebaseMessaging.getInstance().subscribeToTopic("topic").addOnCompleteListener { task ->
	        if (!task.isSuccessful) {
	            //subscribe failed
	        }
	        //subscribe success
	    }
	}

	private fun unsubscribeFromTopic() {
	    FirebaseMessaging.getInstance().unsubscribeFromTopic("topic").addOnCompleteListener { task ->
	        if (!task.isSuccessful) {
	            //unsubscribe failed
	        }
	        //unsubscribe success
	    }
	}
}