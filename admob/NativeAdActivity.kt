class NativeAdActivity : AppCompatActivity() {

    private lateinit var adLoader: AdLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad)
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
        loadUnifiedNativeAd()
    }

    private fun loadUnifiedNativeAd() {
        adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
            .forUnifiedNativeAd { ad : UnifiedNativeAd ->
                showUnifiedNativeAd(parentView, ad)
            }
            .withAdListener(object: AdListener() {
                //add custom behavior to listener methods
                override fun onAdClicked() {
                    //some action
                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder() //setup builder
                .setImageOrientation(ORIENTATION_PORTRAIT)
                .build())
            .build()

        adLoader.loadAd(AdRequest.Builder().build()) //or use loadAds to load multiple items
    }

    private fun showUnifiedNativeAd(parent: ViewGroup, ad: UnifiedNativeAd) {
        //inflate view where adView root should be UnifiedNativeAdView instance
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val adView = inflater.inflate(R.layout.custom_ad_layout, null) as UnifiedNativeAdView

        //use ad data into some views like text or image
        val headlineView = adView.findViewById<TextView>(R.id.adHeadline)
        headlineView.text = ad.headline
        adView.headlineView = headlineView

        //clear parent and put new add
        adView.setNativeAd(ad)
        parent.removeAllViews() 
        parent.addView(adView)
    }
}