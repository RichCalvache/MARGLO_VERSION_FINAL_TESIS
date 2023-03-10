package com.example.marglo.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marglo.Entidades.ActividadVo;
import com.example.marglo.FragmentsDiscriminacion.ListaDiscriminacionFragment;
import com.example.marglo.R;

import java.util.ArrayList;


public class AdaptadorActividadesFonetica extends RecyclerView.Adapter<AdaptadorActividadesFonetica.ViewHolderActividades> {

    private ListaDiscriminacionFragment recyclerClickActividadesInterface;
    ArrayList<ActividadVo> listaActividades;

    public AdaptadorActividadesFonetica(ArrayList<ActividadVo> listaActividades, ListaDiscriminacionFragment recyclerClickActividadesInterface) {
        this.listaActividades = listaActividades;
        this.recyclerClickActividadesInterface = recyclerClickActividadesInterface;
    }


    @Override
    public AdaptadorActividadesFonetica.ViewHolderActividades onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_actividades_discriminacion,null,false);
        //Se agrega esto para que al rotar ocupe toda la pantalla
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new AdaptadorActividadesFonetica.ViewHolderActividades(view );
    }

    @Override
    public void onBindViewHolder(AdaptadorActividadesFonetica.ViewHolderActividades holder, int position) {

        holder.etiNombre.setText(listaActividades.get(position).getNombre());
        holder.etiInformacion.setText(listaActividades.get(position).getInfo());
        holder.foto.setImageResource(listaActividades.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaActividades.size();
    }

    public class ViewHolderActividades extends RecyclerView.ViewHolder {
        TextView etiNombre, etiInformacion;
        ImageView foto;


        public ViewHolderActividades(View itemView) {
            super(itemView);

            etiNombre = (TextView) itemView.findViewById(R.id.idNombre);
            etiInformacion = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    recyclerClickActividadesInterface.onItemClick(getAbsoluteAdapterPosition());

                }
            });

        }
    }
}
