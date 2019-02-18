class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startRemoteConfig()
    }

    private fun startRemoteConfig() {
	    //get instance
	    val remoteConfig = FirebaseRemoteConfig.getInstance()

	    //set remote config singleton
	    val configSettings = FirebaseRemoteConfigSettings.Builder()
	        .setDeveloperModeEnabled(BuildConfig.DEBUG) //for developer purpose
	        .build()
	    remoteConfig.setConfigSettings(configSettings)

	    //set defaults value from xml or Map object
	    remoteConfig.setDefaults(R.xml.remote_config_default)

	    //fetch parameters from remote config and add some listeners
	    remoteConfig.fetch(36000L) //cache expiration for 10h
	        .addOnCompleteListener(this) { task ->
	            if (task.isSuccessful) {
	                remoteConfig.activateFetched() //active new data before use
	                //do some action
	            } 
	            else {
	                //do some action
	            }

	            //get current values and use them in some variable, preferences etc
	            val showTutorial = remoteConfig.getBoolean("show_tutorial")
	            val skin = remoteConfig.getString("skin")
	            //do something with fetched values
	        }
	}
}