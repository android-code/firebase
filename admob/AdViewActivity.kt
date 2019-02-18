class AdViewActivity : AppCompatActivity() {

    private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adview)
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")

        setAdView()
    }

    private fun setAdView() {
        adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        //setup listener methods
        adView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                //some additional action when ad loaded
            }
            override fun onAdFailedToLoad(errorCode: Int) {}
            override fun onAdOpened() {}
            override fun onAdLeftApplication() {}
            override fun onAdClosed() {}
        }
    }
}