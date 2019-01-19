class MainActivity : AppCompatActivity() {

    private lateinit var trace: Trace

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trace = FirebasePerformance.getInstance().newTrace("activity_creation")
        trace.start()

        setListeners()

        //do some work
    }

    //more lifecycle methods

    override fun onResume() {
        super.onResume()
        trace.stop()
    }

    private fun setListeners() {
        buttonAddItem.setOnClickListener {
            addItem(editText.text.toString())
        }
        buttonAddItemWithoutMetrics.setOnClickListener {
            addItemWithoutMetrics(editText.text.toString())
        }
        buttonDownload.setOnClickListener {
            val link = editText.text.toString()
            Thread( Runnable { download(link) }).start()
        }
    }

    private fun addItem(item: String) {
        //start trace
        val addItemTrace = FirebasePerformance.getInstance().newTrace("add_item_trace")
        addItemTrace.start()

        //set metrics and attributes
        addItemTrace.incrementMetric("text_length", item.length.toLong())
        addItemTrace.putAttribute("item", item) //avoid personal info

        //update UI
        val text = textViewItems.text
        textViewItems.text = "$text \n $item"

        //stop trace
        addItemTrace.stop()
    }

    @AddTrace(name = "add_item_trace_without_metrics")
    private fun addItemWithoutMetrics(item: String) {
        val text = textViewItems.text
        textViewItems.text = "$text \n $item"
    }

    private fun download(link: String) {
        //run on background thread
        val url = URL(link)
        val metric = FirebasePerformance.getInstance().newHttpMetric(link, FirebasePerformance.HttpMethod.GET)
        metric.start()

        //set connections
        val conn = url.openConnection() as HttpURLConnection
        conn.doOutput = true
        conn.setRequestProperty("Content-Type", "application/json")
        val content = convertStreamToString(conn.outputStream) //do something with content

        metric.setHttpResponseCode(conn.responseCode)
        conn.disconnect()
        metric.stop()
    }

    private fun convertStreamToString(outputStream: OutputStream): String {
        return "webpage content" //mock
    }
}