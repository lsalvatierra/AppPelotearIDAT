package pe.edu.idat.apppelotearidat;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pe.edu.idat.apppelotearidat.Adapters.NoticiasAdapter;
import pe.edu.idat.apppelotearidat.Adapters.ReservasAdapter;
import pe.edu.idat.apppelotearidat.Modelo.Noticia;
import pe.edu.idat.apppelotearidat.Modelo.Reserva;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDashboard.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDashboard extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView rvDatos, rvDatosReservas;
    private NoticiasAdapter adapter;
    private ReservasAdapter adapterReserva;
    ArrayList<Noticia> vDatos;
    ArrayList<Reserva> vDatosReserva;

    private RequestQueue mQueue;

    public FragmentDashboard() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentDashboard newInstance(String param1, String param2) {
        FragmentDashboard fragment = new FragmentDashboard();
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
        View view = inflater.inflate(R.layout.fragment_fragment_dashboard, container, false);

        rvDatos = view.findViewById(R.id.rvDatos);
        rvDatos.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.HORIZONTAL, false
        ));
        adapter = new NoticiasAdapter(getActivity());
        rvDatos.setAdapter(adapter);
        vDatos = new ArrayList<Noticia>();
        /*RESERVASSS*/
        rvDatosReservas = view.findViewById(R.id.rvDatosReservas);
        rvDatosReservas.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterReserva = new ReservasAdapter(getActivity());
        rvDatosReservas.setAdapter(adapterReserva);
        vDatosReserva = new ArrayList<Reserva>();
        SharedPreferences preferences = getActivity().getSharedPreferences("appPelotear", MODE_PRIVATE);
        Integer idPersona = Integer.parseInt(preferences.getString("idpersona", ""));
        //Instanciamos la cola de peticiones.
        mQueue = Volley.newRequestQueue(getActivity());
        //Llamar al método ConsumirWS
        ConsumirWS();
        ConsumirWSReservas(idPersona);
        return view;
        //return inflater.inflate(R.layout.fragment_fragment_dashboard, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void ConsumirWS() {
        //Inicializar el URL del servicio web.
        String url = "http://luis.wordlatin.com/RestfulService/noticias.php";
        //Instanciar el objeto request para que sea agregado
        // a la cola de requests.
        JsonArrayRequest requestNoticias = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i = 0; i < response.length(); i++){
                                JSONObject objImagen = response.getJSONObject(i);
                                Log.i("INFO", objImagen.getString("titulo"));
                                vDatos.add(new Noticia(objImagen.getString("titulo"),
                                        objImagen.getString("urlimagen")));

                            }
                            adapter.agregarElemento(vDatos);
                        }catch (JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                }
        );
        mQueue.add(requestNoticias);
    }

    private void ConsumirWSReservas(int idPersona) {
        //Inicializar el URL del servicio web.
        String url = "http://luis.wordlatin.com/RestfulService/reservas.php";
        Map<String, Integer> params = new HashMap();
        params.put("idpersona", idPersona);
        JSONObject parameters = new JSONObject(params);
        JSONArray arrayreq = new JSONArray();
        arrayreq.put(parameters);
        //Instanciar el objeto request para que sea agregado
        // a la cola de requests.
        JsonArrayRequest requestReserva = new JsonArrayRequest(
                Request.Method.POST, url, arrayreq,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i = 0; i < response.length(); i++){
                                JSONObject objImagen = response.getJSONObject(i);
                                Log.i("INFORES", objImagen.getString("nombresede"));
                                vDatosReserva.add(new Reserva(objImagen.getString("nombresede"),
                                        objImagen.getString("nombreamb"),
                                        objImagen.getString("urlimagen"),
                                        objImagen.getString("HoraIni"),
                                        objImagen.getString("HoraFin"),
                                        objImagen.getString("fecha")));

                            }
                            adapterReserva.agregarElemento(vDatosReserva);
                        }catch (JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                }
        );
        mQueue.add(requestReserva);
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
