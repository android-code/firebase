class SecondActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val city = "Poznań" //get property from some place
        firebaseAnalytics.setUserProperty("city", city)
    }
}