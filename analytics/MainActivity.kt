class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        buttonAction.setOnClickListener {
            textView.setText(editText.text.toString())
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, textView.text.toString())
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text")
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        }

        buttonNavigate.setOnClickListener {
            firebaseAnalytics.logEvent("button_navigate_clicked", null)
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}