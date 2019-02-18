class CreateLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_link)

        //some buttons click listeners  
    }

    private fun prepareDynamicLink() {
	    val dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
	        .setLink(Uri.parse("http://www.androidcode.pl/"))
	        .setDomainUriPrefix("http://androidcode.page.link")
	        //set parameters for target platforms like Android, iOS, GoogleAnalytics or provide social media metatags
	        .setAndroidParameters(DynamicLink.AndroidParameters.Builder("pl.androidcode")
	            .setMinimumVersion(2) //only for apps with version 2 code
	            .build())
	        .setSocialMetaTagParameters(DynamicLink.SocialMetaTagParameters.Builder()
	            .setTitle("Title")
	            .setDescription("Description")
	            .build())
	        .buildDynamicLink()

	    val dynamicLinkUri = dynamicLink.uri

	    //the link could be like below:
	    //http://androidcode.page.link/?link=http://www.androidcode.pl&apn=pl.androidcode&amv=2&st=title&sd=description
	}

	private fun prepareShortDynamicLink() {
	    val shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
	        .setLink(Uri.parse("http://www.androidcode.pl/"))
	        .setDomainUriPrefix("http://androidcode.page.link")
	        .setAndroidParameters(DynamicLink.AndroidParameters.Builder("pl.androidcode").build())
	        .buildShortDynamicLink(ShortDynamicLink.Suffix.SHORT) //pass this arg to get shorten sufix
	        .addOnSuccessListener { result ->
	            val shortLink = result.shortLink
	            val previewLink = result.previewLink
	        }.addOnFailureListener {
	            //some action
	        }

	    //the link could be like below:
	    //http://androidcode.page.link/abcd
	}
}