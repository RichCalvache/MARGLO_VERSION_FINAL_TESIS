package com.example.marglo.FragmentsIdentificaciÃ³n;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.marglo.Entidades.DBHelperPuntajes;
import com.example.marglo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Actividad_ident_2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_ident_2Fragment extends Fragment {

    //Declaracion de variables
    private ImageView imagen1,imagen2;
    private Button iniciar, parar, instrucciones;
    private TextView textViewAciertos, textViewInstrucc, textViewPregunta, textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonidoSingular, sonidoPlural;
    int i, aciertos_actividad, global_retardo;
    int numero_sonidoSingular, numero_sonidoPlural, numero_imagenSingular, numero_imagenPlural, randomImagenes;

    int randomInicial;

    LottieAnimationView animacionSonido;


    String infoActividad = "Nada que agregar";


    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler;
    private Runnable runnable_iniciar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_ident_2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_ident_2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_ident_2Fragment newInstance(String param1, String param2) {
        Actividad_ident_2Fragment fragment = new Actividad_ident_2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actividad_ident_2, container, false);
    }


    //creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imagen1 = view.findViewById(R.id.imagen1);
        imagen2 = view.findViewById(R.id.imagen2);
        iniciar = view.findViewById(R.id.btn_iniciar);
        parar = view.findViewById(R.id.btn_parar);
        instrucciones = view.findViewById(R.id.btn_instrucciones);

        textViewAciertos = view.findViewById(R.id.textViewAciertosid);
        textViewFallaste = view.findViewById(R.id.textViewFallasteid);
        textViewInstrucc = view.findViewById(R.id.textviewInstrucc);
        //textViewPregunta = view.findViewById(R.id.textViewPreguntaid);
        textViewSeleccione = view.findViewById(R.id.textviewSeleccioneid);
        textViewEscuche = view.findViewById(R.id.textViewEscucheid);

        animacionSonido = view.findViewById(R.id.animacion_sonido);



        //Lista de sonidos frases
        List<Integer> ListaSonidosSingular = new ArrayList<Integer>();
        ListaSonidosSingular.add(R.raw.abuelo);
        ListaSonidosSingular.add(R.raw.anillo);
        ListaSonidosSingular.add(R.raw.balon);
        ListaSonidosSingular.add(R.raw.bolso);
        ListaSonidosSingular.add(R.raw.boton);
        ListaSonidosSingular.add(R.raw.bufanda);
        ListaSonidosSingular.add(R.raw.caballo);
        ListaSonidosSingular.add(R.raw.cama);
        ListaSonidosSingular.add(R.raw.carro);
        ListaSonidosSingular.add(R.raw.casa);
        ListaSonidosSingular.add(R.raw.jaula);
        ListaSonidosSingular.add(R.raw.perro);


        //Lista de sonidos sonidos
        List<Integer> ListaSonidosPlural = new ArrayList<Integer>();
        ListaSonidosPlural.add(R.raw.abuelos);
        ListaSonidosPlural.add(R.raw.anillos);
        ListaSonidosPlural.add(R.raw.balones);
        ListaSonidosPlural.add(R.raw.bolsos);
        ListaSonidosPlural.add(R.raw.botones);
        ListaSonidosPlural.add(R.raw.bufandas);
        ListaSonidosPlural.add(R.raw.caballos);
        ListaSonidosPlural.add(R.raw.camas);
        ListaSonidosPlural.add(R.raw.carros);
        ListaSonidosPlural.add(R.raw.casas);
        ListaSonidosPlural.add(R.raw.jaulas);
        ListaSonidosPlural.add(R.raw.perros);

        //Lista de imagenes frases
        List<Integer> ListaImagenesSingular = new ArrayList<Integer>();
        ListaImagenesSingular.add(R.drawable.abuelo_singular);
        ListaImagenesSingular.add(R.drawable.anillo_singular);
        ListaImagenesSingular.add(R.drawable.balon_singular);
        ListaImagenesSingular.add(R.drawable.bolso_singular);
        ListaImagenesSingular.add(R.drawable.boton_singular);
        ListaImagenesSingular.add(R.drawable.bufanda_singular);
        ListaImagenesSingular.add(R.drawable.caballo_singular);
        ListaImagenesSingular.add(R.drawable.cama_singular);
        ListaImagenesSingular.add(R.drawable.carro_singular);
        ListaImagenesSingular.add(R.drawable.casa_ident_3);
        ListaImagenesSingular.add(R.drawable.jaula_singular);
        ListaImagenesSingular.add(R.drawable.perro_singular);

        //Lista de imagenes sonidos
        List<Integer> ListaImagenesPlural = new ArrayList<Integer>();
        ListaImagenesPlural.add(R.drawable.abuelos_plural);
        ListaImagenesPlural.add(R.drawable.anillos_plural);
        ListaImagenesPlural.add(R.drawable.balones_plural);
        ListaImagenesPlural.add(R.drawable.bolsos_plural);
        ListaImagenesPlural.add(R.drawable.botones_plural);
        ListaImagenesPlural.add(R.drawable.bufandas_plural);
        ListaImagenesPlural.add(R.drawable.caballlos_plural);
        ListaImagenesPlural.add(R.drawable.camas_plural);
        ListaImagenesPlural.add(R.drawable.carros_plural);
        ListaImagenesPlural.add(R.drawable.casas);
        ListaImagenesPlural.add(R.drawable.jaulas_plural);
        ListaImagenesPlural.add(R.drawable.perros_plural);

        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        // Initialize a new Handler
        handler = new Handler();


//RUNNABLE O TIMER PARA EJECUTAR PROCESO CADA CIERTO TIEMPO
        //final Handler handler = new Handler();
        //final Runnable runnable = new Runnable() {
        runnable_iniciar = new Runnable() {
            public void run() {
                Log.d("Runnable", "Handler is working");


                //Genera un numero entre 0 y 1, para reproducir sonido o frase, y de acuerdo a este dar respuesta tambien
                Random rand = new Random();
                randomInicial = rand.nextInt(2);

                if(randomInicial==0){//Reproduce singular
                    // Generacion Aleatoria de numeros imagenes y sonidos
                    int randomIntListaSingular = (new Random().nextInt(ListaSonidosSingular.size()));
                    //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                    numero_sonidoSingular = ListaSonidosSingular.get(randomIntListaSingular);
                    //Crea la instancia  del sonido de acuerdo a la posicion int
                    sonidoSingular = MediaPlayer.create(getActivity(), numero_sonidoSingular);
                    //Imagenes con sonidos correspondientes
                    //int randomIntListaImagenesSonidos = (new Random().nextInt(ListaImagenesPlural.size()));
                    // Crea la instancia  de la imagen de acuerdo a la posicion int
                    numero_imagenSingular = ListaImagenesSingular.get(randomIntListaSingular);
                    numero_imagenPlural = ListaImagenesPlural.get(randomIntListaSingular);


                }else{//Reproduce plural
                    // Generacion Aleatoria de numeros imagenes y sonidos
                    int randomIntListaPlural = (new Random().nextInt(ListaSonidosPlural.size()));
                    //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                    numero_sonidoPlural = ListaSonidosPlural.get(randomIntListaPlural);
                    //Crea la instancia  del sonido de acuerdo a la posicion int
                    sonidoPlural = MediaPlayer.create(getActivity(), numero_sonidoPlural);
                    //Imagenes con sonidos correspondientes
                    //int randomIntListaImagenesFrases = (new Random().nextInt(ListaImagenesSingular.size()));
                    // Crea la instancia  de la imagen de acuerdo a la posicion int
                    numero_imagenPlural = ListaImagenesPlural.get(randomIntListaPlural);
                    numero_imagenSingular = ListaImagenesSingular.get(randomIntListaPlural);
                }




                //para que salga el texto "escuche los sonidos"
                textViewEscuche.setVisibility(View.VISIBLE);
                //textViewPregunta.setVisibility(View.INVISIBLE);
                //para ocultar las imagenes 1  y 2 nuevamente
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);


                //para activar nuevamente la opcion clickeable de las imagenes
                imagen1.setClickable(true);
                imagen2.setClickable(true);


                //Reproducir aleatoria sonidos cortos o largos
                reproducirSonido();


                //Retardo
                //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 30000;

                if (i >= 13) { // just remove call backs//
                    //para parar el sonido correctamente
                    if(sonidoSingular != null) {
                        sonidoSingular.stop();
                        sonidoSingular.release();
                        sonidoSingular = null;
                    }
                    if(sonidoPlural != null) {
                        sonidoPlural.stop();
                        sonidoPlural.release();
                        sonidoPlural = null;
                    }
                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                    iniciar.setVisibility(View.VISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewEscuche.setVisibility(View.INVISIBLE);


                    handler.removeCallbacks(this);
                    //Log.d("Runnable", "ok");

                    if (aciertos_actividad >= 10) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        //navControllermio.navigate(R.id.actividad_conc_3_felicitacionFragment);
                        navControllermio.navigate(R.id.action_actividad_discr_4Fragment_to_actividad_discr_4_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_discr_4Fragment_to_actividad_discr_4_erradoFragment);//pasar a otro fragment con actions

                        guardarPuntajeBD();
                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                    }
                }
                else{ // post again
                    i++;
                    handler.removeCallbacks(this);
                    handler.postDelayed(this, global_retardo);//Repite el proceso cada cierto tiempo, en este caso globarl_retardo al azar
                }
            }
        };

//FUNCION PARA EL BOTON INICIAR
        iniciar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                i=0;
                //variables

                aciertos_actividad = 0;
                //int retardo = (int) (Math.random() * 3000) + 1; //genera retardo aleatoriamente entre un rango 1 - n
                int retardo =3000;
                //defino visibilidad objetos botones y textos
                parar.setVisibility(View.VISIBLE);
                iniciar.setVisibility(View.INVISIBLE);
                instrucciones.setVisibility(View.INVISIBLE);

                textViewInstrucc.setVisibility(View.INVISIBLE);


                //inicio el runnable despues de 1-3 segundos
                handler.removeCallbacks(runnable_iniciar);
                handler.postDelayed(runnable_iniciar, retardo);
            }
        });

//FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonidoSingular != null) {
                    sonidoSingular.stop();
                    sonidoSingular.release();
                    sonidoSingular = null;
                }
                if(sonidoPlural != null) {
                    sonidoPlural.stop();
                    sonidoPlural.release();
                    sonidoPlural = null;
                }

                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);

                animacionSonido.setVisibility(View.INVISIBLE);

                parar.setVisibility(View.INVISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                instrucciones.setVisibility(View.VISIBLE);

                textViewEscuche.setVisibility(View.INVISIBLE);
                //textViewPregunta.setVisibility(View.INVISIBLE);
                textViewSeleccione.setVisibility(View.INVISIBLE);
                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewFallaste.setVisibility(View.INVISIBLE);

                handler.removeCallbacks(runnable_iniciar);
                //Log.d("Runnable", "ok");
            }
        });

        //FUNCION PARA EL BOTON INSTRUCCIONES
        instrucciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_2_ayudasFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_ident_2Fragment_to_actividad_ident_2_instruccionesFragment); //pasar a otro fragment con actions

                handler.removeCallbacks(runnable_iniciar);
                //Log.d("Runnable", "ok");
            }
        });


        //FUNCION PARA EL BOTON AYUDAS
       /* opciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_3_opcionesFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_conc_3Fragment_to_actividad_conc_3_opcionesFragment); //pasar a otro fragment con actions
                handler.removeCallbacks(runnable_iniciar);
                Log.d("Runnable", "ok");


            }
        });*/



//funciones on clicklistener para las imagenes
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seleccionarRespuesta1();

            }
        });
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seleccionarRespuesta2();

            }
        });



    }//onviewcreated




//MÃTODOS UTILIZADOS



    private void reproducirSonido(){


        if(randomInicial==0){//Reproduce frase

            //Reproducir sonido frase
            sonidoSingular.start();
            animacionSonido.setVisibility(View.VISIBLE);

            //una vez acabado el sonidoFrase muestra las imagenes
            sonidoSingular.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer player) {
                    sonidoSingular.stop();
                    sonidoSingular.release();
                    sonidoSingular = null;
                    animacionSonido.setVisibility(View.INVISIBLE);

                    mostrarImagenesAleatorio();
                }
            });

        }else{//Reproduce sonido
            //Reproducir sonido
            sonidoPlural.start();
            animacionSonido.setVisibility(View.VISIBLE);

            //una vez acabado el sonidoFrase muestra las imagenes
            sonidoPlural.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer player) {
                    sonidoPlural.stop();
                    sonidoPlural.release();
                    sonidoPlural = null;

                    animacionSonido.setVisibility(View.INVISIBLE);

                    mostrarImagenesAleatorio();
                }
            });

        }



    }




    private void mostrarImagenesAleatorio(){

        imagen1.setBackgroundResource(R.color.white);
        imagen2.setBackgroundResource(R.color.white);

        imagen1.setVisibility(View.VISIBLE);
        imagen2.setVisibility(View.VISIBLE);

        //textViewPregunta.setVisibility(View.VISIBLE);
        //textViewPregunta.setText(R.string.pregunta_singular_o_plural);

        textViewSeleccione.setVisibility(View.VISIBLE);
        textViewSeleccione.setText(R.string.Seleccione_una_imagen);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
        textViewEscuche.setVisibility(View.INVISIBLE);

        Random rand = new Random();
        //int randomImagenes= rand.nextInt(2);
        randomImagenes= rand.nextInt(2);
        if(randomImagenes==0){//para que muestre la imagen de la frase en imageview1 y la del sonido en el imageview2

            imagen1.setImageResource(numero_imagenSingular); //imagen1 = imagenFrase
            imagen2.setImageResource(numero_imagenPlural); //imagen2 = imagenSonido

        }else{//para que muestre la imagen de la frase en imageview2 y la del sonido en el imageview1

            imagen2.setImageResource(numero_imagenSingular); //imagen1 = imagenFrase
            imagen1.setImageResource(numero_imagenPlural); //imagen2 = imagenSonido

        }
    }



    private void seleccionarRespuesta1(){
        if(randomInicial==0){//0 = pregunta frase
            if(randomImagenes==0){//0 = pone imagen frase en imageview1 y imagen sonido en imageview2
                //Acierta
                imagen1.setImageResource(R.drawable.check); //imagen1 = imagencorto
                imagen1.setBackgroundResource(R.drawable.estilo_imagen_verde);
                imagen1.setClickable(false);

                imagen2.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad + 1;
                mostrarTextviewAciertos();
                //pasar al siguiente sonido
                iniciarRunnable();

            }else{//1 = pone imagen frase en imageview2 y imagen sonido en imageview1
                //se equivoca
                imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen1.setClickable(false);

                imagen2.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();
            }


        }else{//1 = pregunta sonido
            if(randomImagenes==0){//0 = pone imagen frase en imageview1 y imagen sonido en imageview2
                //se equivoca
                imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen1.setClickable(false);

                imagen2.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();

            }else{//1 = pone imagen frase en imageview2 y imagen sonido en imageview1
                //Acierta
                imagen1.setImageResource(R.drawable.check); //imagen1 = imagencorto
                imagen1.setBackgroundResource(R.drawable.estilo_imagen_verde);
                imagen1.setClickable(false);

                imagen2.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad + 1;
                mostrarTextviewAciertos();
                //pasar al siguiente sonido
                iniciarRunnable();

            }
        }
    }

    private void seleccionarRespuesta2(){
        if(randomInicial==0){//0 = pregunta frase
            if(randomImagenes==0){//0 = pone imagen frase en imageview1 y imagen sonido en imageview2
                //se equivoca
                imagen2.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen2.setClickable(false);

                imagen1.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();


            }else{//1 = pone imagen frase en imageview2 y imagen sonido en imageview1
                //Acierta
                imagen2.setImageResource(R.drawable.check); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_verde);
                imagen2.setClickable(false);

                imagen1.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad + 1;
                mostrarTextviewAciertos();
                //pasar al siguiente sonido
                iniciarRunnable();

            }


        }else{//1 = pregunta sonido
            if(randomImagenes==0){//0 = pone imagen frase en imageview1 y imagen sonido en imageview2
                //Acierta
                imagen2.setImageResource(R.drawable.check); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_verde);
                imagen2.setClickable(false);

                imagen1.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad + 1;
                mostrarTextviewAciertos();
                //pasar al siguiente sonido
                iniciarRunnable();

            }else{//1 = pone imagen frase en imageview2 y imagen sonido en imageview1
                //se equivoca
                imagen2.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen2.setClickable(false);

                imagen1.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();

            }
        }
    }



    private void iniciarRunnable(){
        //Iniciar runnable dependiendo las opciones seleccionadas
        //global_retardo = (int) (Math.random() * 4000) + 2000; //genera retardo aleatoriamente entre un rango 1 - n
        //cargarPreferenciasAyudas();
        int retardo = 5000;
        handler.removeCallbacks(runnable_iniciar);
        handler.postDelayed(runnable_iniciar, retardo);
    }

    //Shared PReferences para puntajes
    private void guardarPreferenciasPuntaje(){
        SharedPreferences preferencespuntaje = getActivity().getSharedPreferences("puntajes_table", Context.MODE_PRIVATE);

        int puntaje = aciertos_actividad;
        SharedPreferences.Editor editor = preferencespuntaje.edit();
        editor.putInt("puntaje", puntaje);
        editor.commit();
    }

    private void guardarPuntajeBD(){
        //guardar puntaje en BD
        int actividadTXT = 2;
        String subhabilidadTXT = "IdentificaciÃ³n";
        int puntajeTXT = aciertos_actividad;
        String infoTXT= infoActividad;//Informacion sobre la actividad (Ayudas)


        String fechaTXT;
        Calendar calendar;
        SimpleDateFormat simpleDateFormat;

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa");
        fechaTXT = simpleDateFormat.format(calendar.getTime()).toString();

        //insertar datos a la bd
        Boolean checkinsertpuntaje = DB.insertPuntaje(fechaTXT, actividadTXT,subhabilidadTXT,puntajeTXT, infoTXT);        if (checkinsertpuntaje == true) {
            Toast.makeText(getContext(), "Puntaje guardado", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "FallÃ³ registro de puntaje!", Toast.LENGTH_SHORT).show();
        }

    }

    private void mostrarTextviewAciertos(){
        textViewEscuche.setVisibility(View.INVISIBLE);
        //textViewPregunta.setVisibility(View.INVISIBLE);

        //handler para que muestre texview aciertos 3 segundos y se desaparezca
        textViewAciertos.setVisibility(View.VISIBLE);
        textViewAciertos.setText("Â¡Muy bien! +" + aciertos_actividad);
        //para mostrar el textview de abajo (textviewselecione)
        textViewSeleccione.setVisibility(View.VISIBLE);//setClickable(false);
        textViewSeleccione.setText(R.string.Correcto);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_verde);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewAciertos.setVisibility(View.INVISIBLE);// do your operation here
                textViewSeleccione.setVisibility(View.INVISIBLE);// do your operation here
                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }



    private void mostrarTextviewFallaste(){
        textViewEscuche.setVisibility(View.INVISIBLE);
        //textViewPregunta.setVisibility(View.INVISIBLE);

        //handler para que muestre la imagen 1 segundo y se desaparezca
        textViewFallaste.setVisibility(View.VISIBLE);
        textViewFallaste.setText(R.string.Fallaste);
        //para mostrar el textview de abajo (textviewselecione)
        textViewSeleccione.setVisibility(View.VISIBLE);//setClickable(false);
        textViewSeleccione.setText(R.string.Incorrecto);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_rojo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewFallaste.setVisibility(View.INVISIBLE);// do your operation here
                textViewSeleccione.setVisibility(View.INVISIBLE);// do your operation here
                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }



    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {
        handler.removeCallbacks(runnable_iniciar);

        if(sonidoSingular != null) {
            sonidoSingular.stop();
            sonidoSingular.release();
            sonidoSingular = null;
        }
        if(sonidoPlural != null) {
            sonidoPlural.stop();
            sonidoPlural.release();
            sonidoPlural = null;
        }
        super.onPause();
    }
}//final
