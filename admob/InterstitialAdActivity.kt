class InterstitialAdActivity : AppCompatActivity() {

    private lateinit var interstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial_ad)
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")

        setInterstitialAd()
        setButtonClickListener()
    }

    private fun setInterstitialAd() {
        interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        interstitialAd.loadAd(AdRequest.Builder().build())

        //setup listener methods
        interstitialAd.adListener = object: AdListener() {
            override fun onAdClosed() {
                //reload new one
                interstitialAd.loadAd(AdRequest.Builder().build())
                //start action from point where was paused
            }
            override fun onAdOpened() {
                //pause some action
            }
        }
    }

    private fun setButtonClickListener() {
        //show ad on some action like click finish button
        finishButton.setOnClickListener {
            if (interstitialAd.isLoaded) {
                interstitialAd.show()
            } 
            else {
                //ad still not loaded    
            }
    }
}