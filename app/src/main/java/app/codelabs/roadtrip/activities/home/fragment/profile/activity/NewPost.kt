//package app.codelabs.roadtrip.activities.home.fragment.profile.activity
//
//import android.Manifest
//import android.app.Activity
//import android.content.pm.PackageManager
//import android.graphics.Color
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import android.view.Window
//import android.view.WindowManager
//import android.widget.Toast
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.activity.viewModels
//import com.google.android.material.snackbar.Snackbar
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.navigateUp
//import androidx.navigation.ui.setupActionBarWithNavController
//import app.codelabs.roadtrip.R
//import app.codelabs.roadtrip.databinding.ActivityNewPostBinding
//import app.codelabs.roadtrip.helpers.RealPath.with
//import com.google.android.gms.cast.framework.media.ImagePicker
//import com.google.gson.Gson
//import okhttp3.FormBody
//import okhttp3.MultipartBody
//import okhttp3.RequestBody
//import org.greenrobot.eventbus.EventBus
//import org.greenrobot.eventbus.Subscribe
//import java.io.File
//
//class NewPost : AppCompatActivity() {
//
//    private val TAG = this.toString()
//    private lateinit var binding :ActivityNewPostBinding
//    private lateinit var shopDetaildata : ShopItemItem
//
//    private val memberViewModel : MemberViewModel by viewModels {
//        InjectorUtils.provideMemberViewModelFactory()
//    }
//    // categoryItemSelected for category
//    private lateinit var categoryItemSelected : ShopCategoryItemItem
//    companion object {
//        private const val CAMERA_PERMISSION_CODE = 100
//        private const val STORAGE_PERMISSION_CODE = 101
//    }
//    var uriList : MutableList<Uri> = ArrayList()
//    var fileList : MutableList<File> = ArrayList()
//    private var itemShopImageUriAdapter = ItemImageURIAdapter()
//    private var itemShopImageAdapter = ItemImageAdapter()
//    private var editMode = false
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityNewPostBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//        binding.toolbar.setNavigationOnClickListener {
//            onBackPressed()
//        }
//
//
//
//        binding.btnSave.setOnClickListener {
//            if (editMode){
//                getEdit()
//            }else {
//                getAddItem()
//            }
//        }
//
//
//        setEvent()
//        checkCondition()
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val window: Window = window
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.setStatusBarColor(Color.WHITE)
//        }
//        checkPermission(
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            STORAGE_PERMISSION_CODE)
//        checkPermission(Manifest.permission.CAMERA,
//            CAMERA_PERMISSION_CODE)
//    }
//
//    private fun getEdit() {
//        val files: MutableList<MultipartBody.Part> = ArrayList()
//        val data = mapOf(
//            "user_store_item_id" to shopDetaildata.id.toString()
//                .toRequestBody(MultipartBody.FORM),
//            "title" to binding.editTextProductTitle.text.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//            "price" to binding.editTextProductPrice.text.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//            "stock" to binding.editTextProductStock.text.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//            "category_id" to categoryItemSelected.id.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//            "description" to binding.editTextProductDescription.text.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//        )
//
//        if (fileList.isNotEmpty()) {
//            for (i in fileList.indices) {
//                val requestBody = fileList[i].asRequestBody("image/*".toMediaTypeOrNull())
//                val part = MultipartBody.Part.createFormData(
//                    "image" + "[" + i.toString() + "]",
//                    fileList[i].name,
//                    requestBody
//                )
//                files.add(part)
//            }
//            postEdit(data, files)
//        } else {
//            postEdit(data, files)
//        }
//    }
//    private fun postEdit(body: Map<String, RequestBody?>, files: List<MultipartBody.Part>){
//        memberViewModel.getEditData(body,files).observe(this){
//            when(it){
//                is ApiCallback.OnLoading ->{
//
//                }
//                is ApiCallback.OnSuccess ->{
//                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                    onBackPressed()
//                }
//                is ApiCallback.OnError -> {
//                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    private fun getAddItem() {
//        if (!CheckValidaty()){
//            return
//        }
//        val files : MutableList<MultipartBody.Part> = ArrayList()
//        val data = mapOf(
//            "user_store_id" to DataManager.getInstance().storeId.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//            "title" to binding.editTextProductTitle.text.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//            "price" to binding.editTextProductPrice.text.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//            "stock" to binding.editTextProductStock.text.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//            "description" to binding.editTextProductDescription.text.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//            "category_id" to categoryItemSelected.id.toString().trim()
//                .toRequestBody(MultipartBody.FORM),
//        )
//        for (i in fileList.indices){
//            val requestBody = fileList[i].asRequestBody("image/*".toMediaTypeOrNull())
//            val part = MultipartBody.Part.createFormData(
//                "image"+"["+i.toString()+"]"
//                ,fileList[i].name
//                ,requestBody)
//            files.add(part)
//        }
//        memberViewModel.getAddData(data,files).observe(this){
//            when(it){
//                is ApiCallback.OnLoading ->{
//
//                }
//                is ApiCallback.OnSuccess ->{
//                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                    onBackPressed()
//                }
//                is ApiCallback.OnError -> {
//                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    private fun CheckValidaty() : Boolean{
//        var allvalid    = true
//        val title       = binding.editTextProductTitle.text.toString().trim()
//        val price       = binding.editTextProductPrice.text.toString().trim()
//        val stock       = binding.editTextProductStock.text.toString().trim()
//        val category    = categoryItemSelected.category.toString().trim()
//        val description = binding.editTextProductDescription.text.toString().trim()
//        val photo       = binding.recylerViewPhoto
//
//        if (title.isEmpty()){
//            Toast.makeText(this, "title Not Empty", Toast.LENGTH_LONG).show()
//            allvalid = false
//        }
//
//        if (category.isEmpty()){
//            Toast.makeText(this, "category Not Empty", Toast.LENGTH_LONG).show()
//            allvalid = false
//        }
//
//        if (price.isEmpty()){
//            Toast.makeText(this, "price Not Empty", Toast.LENGTH_LONG).show()
//            allvalid = false
//        }
//
//        if (stock.isEmpty()){
//            Toast.makeText(this, "stock Not Empty", Toast.LENGTH_LONG).show()
//            allvalid = false
//        }
//
//        if (description.isEmpty()){
//            Toast.makeText(this, "descriptiom Not Empty", Toast.LENGTH_LONG).show()
//            allvalid = false
//        }
//
//        if (photo.isEmpty()){
//            Toast.makeText(this, "photo Not Empty", Toast.LENGTH_LONG).show()
//            allvalid = false
//        }
//        return allvalid
//    }
//
//    private fun checkCondition() {
//        if (intent.extras?.getString("data") !=null){
//            editMode = true
//            val json = intent.extras!!.getString("data")
//            shopDetaildata = Gson().fromJson(json,ShopItemItem::class.java)
//            categoryItemSelected = ShopCategoryItemItem(shopDetaildata.category.id,shopDetaildata.category.category)
//            itemShopImageAdapter.setData(shopDetaildata.image.toMutableList())
//            binding.recyclerViewPhotoUrl.adapter = itemShopImageAdapter
//            binding.toolbar.title = "Edit Post"
//            binding.editTextProductTitle.setText(shopDetaildata.name)
//            binding.editTextProductPrice.setText(shopDetaildata.price.toString())
//            binding.editTextProductStock.setText(shopDetaildata.stock.toString())
//            binding.editTextProductDescription.setText(shopDetaildata.description)
//            binding.editTextProductCategory.text = categoryItemSelected.category
//            binding.recylerViewPhoto.adapter = itemShopImageUriAdapter
//
//        }else{
//            binding.recylerViewPhoto.adapter = itemShopImageUriAdapter
//        }
//    }
//
//    private fun setEvent() {
//        binding.imageNewPost.setOnClickListener {
//            ImagePicker.with(this)
//                .crop()
//                .compress(1024)
//                .maxResultSize(1080,1080)
//                .createIntent { intent -> startForProfileImageResult.launch(intent) }
//        }
//        binding.viewArrowCategory.setOnClickListener {
//            CategoryBottomSheetDialog().show(supportFragmentManager,TAG)
//        }
//        itemShopImageUriAdapter.onDeletePosition = {
//            removeFile(it)
//        }
//
//    }
//
//    private fun removeFile(pos : Int){
//        fileList.removeAt(pos)
//    }
//
//    private val startForProfileImageResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            val resultCode = result.resultCode
//            val data = result.data
//
//            if (resultCode == Activity.RESULT_OK) {
//                val fileUri = data?.data!!
//
//                uriList.add(fileUri)
//                fileList.add(File(fileUri.path.toString()))
//                checkAdapter()
//            } else if (resultCode == ImagePicker.RESULT_ERROR) {
//                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//            } else {
//            }
//        }
//
//    private fun checkAdapter() {
//        itemShopImageUriAdapter.setData(uriList)
//        Log.e("TAG", "checkAdapter: "+fileList.size.toString() )
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == CAMERA_PERMISSION_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            } else {
//
//            }
//        } else if (requestCode == STORAGE_PERMISSION_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            } else {
//
//            }
//        }
//    }
//
//    private fun checkPermission(permission: String, requestCode: Int) {
//        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
//            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
//        } else {
//
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        EventBus.getDefault().register(this)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        EventBus.getDefault().unregister(this)
//    }
//
//    @Subscribe
//    fun onEventSelectShopItemCategory(data : EventSelectShopItemCategory){
//        categoryItemSelected = data.data
//        binding.textProductCategory.text = data.data.category
//    }
//
//    @Subscribe
//    fun onEventDetailEvent(data: EventItemImageDelete){
//        val formBuilder = FormBody.Builder()
//        formBuilder.add("user_store_item_image_id",data.data.id.toString())
//        memberViewModel.getRemoveImage(formBuilder.build()).observe(this){
//            when(it){
//                is ApiCallback.OnLoading ->{
//
//                }
//                is ApiCallback.OnSuccess ->{
//                    itemShopImageAdapter.notifyDataSetChanged()
//                }
//                is ApiCallback.OnError -> {
//                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//}