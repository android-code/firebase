class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiveDynamicLink() 
    }

    private fun receiveDynamicLink() {
	    FirebaseDynamicLinks.getInstance()
	        .getDynamicLink(intent)
	        .addOnSuccessListener(this) { pendingDynamicLinkData ->
	            // Get deep link from result (may be null if no link is found)
	            var deepLink: Uri? = null
	            if (pendingDynamicLinkData != null) {
	                deepLink = pendingDynamicLinkData.link
	            }
	        }.addOnFailureListener {
	            //some action
	        }
	}
}