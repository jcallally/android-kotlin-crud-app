package com.example.trabajoandroid.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajoandroid.R
import com.example.trabajoandroid.models.Laptop
import com.example.trabajoandroid.utils.SharedPref

class LaptopsAdapter(private val context: Activity, private var laptops: ArrayList<Laptop>): RecyclerView.Adapter<LaptopsAdapter.LaptopsViewHolder>()  {

    private val sharedPref = SharedPref(context)
    private var formatPrice: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_laptops, parent, false)
        return LaptopsViewHolder(view)
    }

    override fun getItemCount(): Int {

        return laptops.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LaptopsViewHolder, position: Int) {

        val listLaptop = laptops[position]

        //Asignamos los datos de la lista al Cardview
        holder.textViewCodigo.text = listLaptop.codigo
        holder.textViewDescripcion.text = listLaptop.descripcion
        holder.textViewMarca.text = listLaptop.marca
        holder.textViewRam.text = listLaptop.ram
        holder.textViewAlmacenamiento.text = listLaptop.almacenamiento
        holder.textViewColor.text = listLaptop.color
        formatPrice = String.format("%.2f", listLaptop.precio)
        holder.textViewPrice.text = "s/. $formatPrice"

        holder.imageViewMenu.setOnClickListener { popupMenu(it, listLaptop, position) } //Menu emergente

        //holder.imageViewDelete.setOnClickListener { deleteItem(position) }
        //holder.imageViewEdit.setOnClickListener { editItem(position) }
    }

    //Menú emergente que se despliega en cada CardView del RecyclerView
    @SuppressLint("DiscouragedPrivateApi", "NotifyDataSetChanged")
    private fun popupMenu(view: View, listLaptop: Laptop, position: Int){

        //CONFIGURACIÓN DE LOS ADAPTADORES DEL SPINNER
        val marcaLaptop = arrayOf("HP", "Lenovo", "Asus", "Samsung", "Toshiba")
        val marcaAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, marcaLaptop)
        marcaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //spinnerMarcaDialog?.adapter = marcasdapter

        val ramLaptop = arrayOf("8GB", "16GB", "32GB", "64GB")
        val ramAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, ramLaptop)
        ramAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //spinnerRamDialog?.adapter = ramAdapter

        val almacenamientoLaptop = arrayOf("256GB", "512GB", "1024GB")
        val almacenamientoAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, almacenamientoLaptop)
        almacenamientoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //spinnerAlmacenamientoDialog?.adapter = almacenamientoAdapter

        //Inicia el menú emergente
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.show_menu)

        popupMenu.setOnMenuItemClickListener {

            when(it.itemId){

                R.id.editText ->
                {

                    //Infla AlertDialog
                    val v = LayoutInflater.from(context).inflate(R.layout.edit_item_dialog, null)

                    //Inicializa items de AlertDialog
                    val textViewCodigoDialog: TextView = v.findViewById(R.id.textview_cod_dialog)
                    val editTextDescripcionDialog: EditText = v.findViewById(R.id.edittext_descripcion_dialog)
                    val spinnerMarcaDialog: Spinner = v.findViewById(R.id.spinner_marca_dialog)
                    val spinnerRamDialog: Spinner = v.findViewById(R.id.spinner_ram_dialog)
                    val spinnerAlmacenamientoDialog: Spinner = v.findViewById(R.id.spinner_almacenamiento_dialog)
                    val editTextPrecioDialog: EditText = v.findViewById(R.id.edittext_precio_dialog)
                    val radioGroupColorDialog: RadioGroup = v.findViewById(R.id.radiogroup_color_dialog)
                    val radioButtonPlateadoDialog: RadioButton = v.findViewById(R.id.radiobutton_plateado_dialog)
                    val radioButtonNegroDialog: RadioButton = v.findViewById(R.id.radiobutton_negro_dialog)

                    //Inicializa el adaptador de los spinner
                    spinnerMarcaDialog.adapter = marcaAdapter
                    spinnerRamDialog.adapter = ramAdapter
                    spinnerAlmacenamientoDialog.adapter = almacenamientoAdapter

                    //Asigna los datos de la lista al AlertDialog-------------
                    textViewCodigoDialog.text = listLaptop.codigo
                    editTextDescripcionDialog.text = Editable.Factory.getInstance().newEditable(listLaptop.descripcion)

                    if (marcaLaptop.contains(listLaptop.marca)){

                        val posicionMarca = marcaLaptop.indexOf(listLaptop.marca)
                        spinnerMarcaDialog.setSelection(posicionMarca)
                    }
                    if (ramLaptop.contains(listLaptop.ram)){

                        val posicionRam = ramLaptop.indexOf(listLaptop.ram)
                        spinnerRamDialog.setSelection(posicionRam)
                    }
                    if (almacenamientoLaptop.contains(listLaptop.almacenamiento)){

                        val posicionAlmacenamiento = almacenamientoLaptop.indexOf(listLaptop.almacenamiento)
                        spinnerAlmacenamientoDialog.setSelection(posicionAlmacenamiento)
                    }

                    editTextPrecioDialog.text = Editable.Factory.getInstance().newEditable(listLaptop.precio.toString())

                    when (listLaptop.color) {

                        "Plateado" -> {
                            radioButtonPlateadoDialog.isChecked = true
                            radioButtonNegroDialog.isChecked = false
                        }
                        "Negro" -> {
                            radioButtonPlateadoDialog.isChecked = false
                            radioButtonNegroDialog.isChecked = true
                        }
                        // Agrega más casos según sea necesario
                        else -> {
                            radioButtonPlateadoDialog.isChecked = false
                            radioButtonNegroDialog.isChecked = false
                        }
                    }
                    //----------------------------------------------------------------------

                    //Construye el AlertDialog
                    AlertDialog.Builder(context)
                        .setView(v)
                        .setPositiveButton("Ok"){

                            //Sintaxis que se utiliza para desestructurar parámetros en lambdas.
                            //Indica que solo estás utilizando el primer parámetro (dialog)
                            // y que estás ignorando el segundo parámetro (_).
                            dialog,_->

                            //Asignamos los datos del AlertDialog a la lista
                            listLaptop.codigo = textViewCodigoDialog.text.toString()
                            listLaptop.descripcion = editTextDescripcionDialog.text.toString()
                            listLaptop.marca = spinnerMarcaDialog.selectedItem.toString()
                            listLaptop.ram = spinnerRamDialog.selectedItem.toString()
                            listLaptop.almacenamiento = spinnerAlmacenamientoDialog.selectedItem.toString()
                            val preString = editTextPrecioDialog.text.toString()
                            listLaptop.precio = preString.toDouble()
                            val colorId = radioGroupColorDialog.checkedRadioButtonId
                            listLaptop.color = if (colorId == R.id.radiobutton_plateado_dialog) "Plateado" else "Negro"

                            notifyItemChanged(position) //Notifica al adaptador cuando solo necesites actualizar un elemento específico en tu lista (Más eficiente - Carga solo el item modificado de la lista)
                            //notifyDataSetChanged() //Notifica al adaptador cuando se realizan cambios que afectan a toda la lista (Menos eficiente - Carga toda la lista)
                            Toast.makeText(context, "Los datos del equipo se actualizaron correctamente", Toast.LENGTH_SHORT).show()
                            dialog.dismiss() //Cierra el cuadro de dialogo

                        }.setNegativeButton("Cancelar"){

                            dialog,_->

                            dialog.dismiss()
                        }
                        .create()
                        .show()

                    true
                }
                R.id.delete ->
                {
                    AlertDialog.Builder(context)
                        .setTitle("Eliminar")
                        .setIcon(R.drawable.ic_warning)
                        .setMessage("¿Estas seguro de eliminar esta información?")
                        .setPositiveButton("Si"){ dialog,_->

                            deleteItem(position)
                            Toast.makeText(context, "Los datos se elminaron correctamente", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()

                        }.setNegativeButton("No"){ dialog,_->

                            dialog.dismiss()
                        }
                        .create()
                        .show()

                    true
                }
                else -> true
            }

        }

        popupMenu.show()

        //CONFIGURACIÓN PARA MOSTRAR ICONOS (Editar, Eliminar) EN EL MENÚ EMERGENTE (All vesions)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            try {

                // 1. Obtiene el campo "mPopup" de la clase PopupMenu mediante reflexión.
                val popup = PopupMenu::class.java.getDeclaredField("mPopup")

                // 2. Hace que el campo "mPopup" sea accesible, incluso si es privado.
                popup.isAccessible = true

                // 3. Obtiene el valor del campo "mPopup" para el objeto PopupMenu proporcionado.
                val menu = popup.get(popupMenu)

                // 4. Utilizando reflexión, obtiene el método "setForceShowIcon" de la clase del menú.
                //    Este método, si está presente, permite mostrar iconos en el menú incluso si no se hace de forma predeterminada.
                //    Invoca el método "setForceShowIcon" y establece su argumento en true para forzar la visualización de iconos.
                menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java).invoke(menu, true)

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }

    private fun deleteItem(position: Int){

        laptops.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, laptops.size)

        sharedPref.save("laptop", laptops)
    }

    //Lista de elemntos encontrados con el SearchView
    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(mList: ArrayList<Laptop>){

        this.laptops = mList
        notifyDataSetChanged()
    }

    class LaptopsViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textViewCodigo: TextView = view.findViewById(R.id.textview_codigo)
        val textViewDescripcion: TextView = view.findViewById(R.id.textview_descripcion)
        val textViewMarca: TextView = view.findViewById(R.id.textview_marca)
        val textViewRam: TextView = view.findViewById(R.id.textview_ram)
        val textViewAlmacenamiento: TextView = view.findViewById(R.id.textview_almacenamiento)
        val textViewColor: TextView = view.findViewById(R.id.textview_color)
        val textViewPrice: TextView = view.findViewById(R.id.textview_precio)
        val imageViewMenu: ImageView = view.findViewById(R.id.imageview_menu)
    }
}