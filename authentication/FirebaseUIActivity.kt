class FirebaseUIActivity : AppCompatActivity() {

    companion object {
        const val SIGN_IN = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_ui)

        //check if user is already signed in
        //if not then show sign in screen

        //add available providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())
        //add more like Twitter, Facebook etc

        //start sign in screen and optional customize by logo, theme and urls
        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.logo)
            .setTheme(R.style.AppTheme)
            .setTosAndPrivacyPolicyUrls("http://androidcode.pl/terms", "http://androidcode.pl/privacy")
            .build()
        startActivityForResult(intent, SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                //success, get user and do some work
                val user = FirebaseAuth.getInstance().currentUser
                startActivity(Intent(this, UserActivity::class.java))
            }
            else {
                //fail, show some error message
            }
        }
    }
}