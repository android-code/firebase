class RewarderVideoAdActivity : AppCompatActivity(), RewardedVideoAdListener {

    private lateinit var rewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewarder_video_ad)
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
        setRewarderVideoAd()
    }

    private fun setRewarderVideoAd() {
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        rewardedVideoAd.rewardedVideoAdListener = this
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", AdRequest.Builder().build())
    }

    //setup RewarderVideoAd instance in lifecycle methods
    override fun onPause() {
        super.onPause()
        rewardedVideoAd.pause(this)
    }

    override fun onResume() {
        super.onResume()
        rewardedVideoAd.resume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        rewardedVideoAd.destroy(this)
    }

    //implement RewardedVideoAdListener methods
    override fun onRewarded(reward: RewardItem) {
        //reward user based on RewardItem instance
    }
    override fun onRewardedVideoAdLeftApplication() {}
    override fun onRewardedVideoAdClosed() {
        //reload new one
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", AdRequest.Builder().build())
    }
    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {}
    override fun onRewardedVideoAdLoaded() {
        //check ad has been showed already
        if(rewardedVideoAd.isLoaded)
            rewardedVideoAd.show()
    }
    override fun onRewardedVideoAdOpened() {}
    override fun onRewardedVideoStarted() {}
    override fun onRewardedVideoCompleted() {}
}