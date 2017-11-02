package gallery.imgur.com.imgurgallery.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import gallery.imgur.com.imgurgallery.api.handlers.GalleryHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.StaggeredGridLayoutManager

import gallery.imgur.com.imgurgallery.helpers.Utils
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import gallery.imgur.com.imgurgallery.adapters.viewholders.GalleryItemViewHolder
import gallery.imgur.com.imgurgallery.ui.BaseActivity
import gallery.imgur.com.imgurgallery.R
import gallery.imgur.com.imgurgallery.adapters.*
import gallery.imgur.com.imgurgallery.api.models.ImgurGalleryItem
import gallery.imgur.com.imgurgallery.observers.GalleryRequestObserver
import gallery.imgur.com.imgurgallery.helpers.Navigator
import gallery.imgur.com.imgurgallery.helpers.SPProvider
import io.reactivex.disposables.Disposable


class MainActivity : BaseActivity(), MainView{


    private val LOG_TAG = this@MainActivity::class.java.name;

    private val mGalleryHandler by lazy { GalleryHandler() }

    private var mStaggeredGridLayoutManager: StaggeredGridLayoutManager = StaggeredGridLayoutManager(2, 1)

    private var mGridLayoutManager: GridLayoutManager = GridLayoutManager(this@MainActivity,2)

    private var mLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(this@MainActivity)

    lateinit var mRecViewLayout : RecyclerViewLayout
    lateinit var mRcAdapter: RecycleViewAdapter
    var mRcAdapterInitialized = false;

    lateinit var mSection : String
    lateinit var mSort : String
    lateinit var mWindow : String
    var mShowViral : Boolean = true
    lateinit var mFullRequest: GlideRequest<Drawable>
    lateinit var mGlideRequest: GlideRequests

    lateinit var mainPresenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(LOG_TAG, "onCreate()")

        setSupportActionBar(toolbar)
        initParameters()
        initRecyclerView()
        initGridTypeButtons()
        requestNews()

        mainPresenter = MainPresenterImpl(this@MainActivity)
    }

    fun initRecyclerView(){
        when(mRecViewLayout){
            RecyclerViewLayout.GridView ->
                recyclerView.layoutManager = mGridLayoutManager
            RecyclerViewLayout.ListView ->
                recyclerView.layoutManager = mLinearLayoutManager
            RecyclerViewLayout.StaggeredGridView ->
                recyclerView.layoutManager = mStaggeredGridLayoutManager
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }

    fun initGridTypeButtons(){
        fab_grid.setOnClickListener {
            recyclerView.clearOnScrollListeners()
            mRecViewLayout = RecyclerViewLayout.GridView
            SPProvider.writeRecyclerViewLayout(baseContext, mRecViewLayout)
            if(recyclerView.adapter!=null) {
                addCllapseOnScrollListener();
                recViewType.collapse()
                initRecyclerView()
            }
        }

        fab_staggered_grid.setOnClickListener {
            recyclerView.clearOnScrollListeners()
            if(recyclerView.adapter!=null) {
                addCllapseOnScrollListener();
            }
            mRecViewLayout = RecyclerViewLayout.StaggeredGridView
            SPProvider.writeRecyclerViewLayout(baseContext, mRecViewLayout)
            recViewType.collapse()
            initRecyclerView()
        }

        fab_list.setOnClickListener {
            recyclerView.clearOnScrollListeners()
            if(recyclerView.adapter!=null) {
                addCllapseOnScrollListener();
            }
            mRecViewLayout = RecyclerViewLayout.ListView
            SPProvider.writeRecyclerViewLayout(baseContext, mRecViewLayout)
            recViewType.collapse()
            initRecyclerView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.getItemId()

        when(id){
            R.id.navigation_settings ->
                Navigator.navigateToSettingsActivity(this@MainActivity)
            R.id.navigation_about ->
                    Navigator.navigateToAboutActivity(this@MainActivity)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.onResume()
        val sort = SPProvider.getSort(baseContext)
        val window = SPProvider.getWindow(baseContext)
        val section = SPProvider.getSection(baseContext)
        val showViral = SPProvider.getShowViral(baseContext)

        if(!sort.equals(mSort) || !window.equals(mWindow) || !section.equals(mSection) || showViral!=mShowViral){
            initParameters()
            requestNews()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        Log.d(LOG_TAG, "onConfigurationChanged()")
        super.onConfigurationChanged(newConfig)
    }

    private fun requestNews() {

        mGalleryHandler.getGalleries(mSection,mSort,mWindow,mShowViral)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (GalleryRequestObserver(this@MainActivity))

    }

    fun addCllapseOnScrollListener(){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when(newState){
                    RecyclerView.SCROLL_STATE_DRAGGING -> recViewType.collapse()
                }
            }
        })
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()

        Log.d(LOG_TAG, "onDestroy()")
    }

    fun initParameters(){
        mRecViewLayout = SPProvider.getRecyclerViewLayout(baseContext)
        mGlideRequest = GlideApp.with(this@MainActivity)
        mFullRequest = mGlideRequest.asDrawable()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(Utils.getGalleryItemWidht(this@MainActivity, mRecViewLayout), resources.getDimensionPixelOffset(R.dimen.image_height))
                .centerCrop()
                .placeholder(ColorDrawable(Color.GRAY))
        mSort = SPProvider.getSort(baseContext)
        mWindow = SPProvider.getWindow(baseContext)
        mSection = SPProvider.getSection(baseContext)
        mShowViral = SPProvider.getShowViral(baseContext)
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun subscriveDisposable(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun setItems(items: List<ImgurGalleryItem>) {
        recViewType.visibility = View.VISIBLE
        if(!mRcAdapterInitialized){
            mRcAdapterInitialized = true;
            mRcAdapter = RecycleViewAdapter(this@MainActivity, items, mRecViewLayout, mainPresenter)
            mRcAdapter.setFullRequest(mFullRequest)
            recyclerView.adapter = mRcAdapter
        }else{
            mRcAdapter.itemList = items;
            mRcAdapter.viewLayout = mRecViewLayout
            mRcAdapter.setFullRequest(mFullRequest)
            mRcAdapter.notifyDataSetChanged()
        }

        recyclerView.setRecyclerListener(RecyclerView.RecyclerListener {
            holder ->
            val tmpHolder = holder as GalleryItemViewHolder
            mGlideRequest.clear(tmpHolder.image)
        })
        addCllapseOnScrollListener();

    }

    override fun showImageView(item: ImgurGalleryItem) {
        Navigator.navigateToImageViewActivity(this@MainActivity, item)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

}
