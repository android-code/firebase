class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDivide.setOnClickListener {
            try {
                //mock exception
                val value = div(10, 0)
                //do more work
            } 
            catch (e: Exception) {
                Crashlytics.logException(e)
            }
        }

        buttonThirdValue.setOnClickListener {
            val values = arrayListOf(1,2) //mock values
            try {
                //mock exception
                val value = getThirdValue(values)
                //do more work
            } 
            catch (e: Exception) {
                Crashlytics.log("buttonThirdValue action error extra log") //to logcat
                Crashlytics.setInt("first_number", values[0])
                Crashlytics.logException(e) //log to Firebase
            }
        }

        //Crashlytics.getInstance().crash(); //run it to force crash
    }

    private fun div(a: Int, b: Int): Int {
        return a/b
    }

    private fun getThirdValue(values: ArrayList<Int>): Int {
        return values[2]
    }
}