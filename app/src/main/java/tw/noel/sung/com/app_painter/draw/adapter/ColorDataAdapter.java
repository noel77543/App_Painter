package tw.noel.sung.com.app_painter.draw.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tw.noel.sung.com.app_painter.databinding.ColorDataAdapterBinding;
import tw.noel.sung.com.app_painter.draw.model.ColorData;

public class ColorDataAdapter extends RecyclerView.Adapter<ColorDataAdapter.ViewHolder> {

    private ArrayList<ColorData> colorData;
    private LayoutInflater inflater;
    private Context context;
    private OnColorChooseListener onColorChooseListener;

    public ColorDataAdapter(Context context) {
        this.context = context;
        colorData = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    //-------

    public void setData(ArrayList<ColorData> colorData) {
        this.colorData = colorData;
        notifyDataSetChanged();
    }

    //--------
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ColorDataAdapterBinding binding = ColorDataAdapterBinding.inflate(inflater, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(colorData.get(position));
    }

    @Override
    public int getItemCount() {
        return colorData.size();
    }

    //---------
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ColorDataAdapterBinding binding;

        public ViewHolder(ColorDataAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        void bind(ColorData colorData) {
            binding.setColorData(colorData);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            onColorChooseListener.onColorChose(getLayoutPosition(),colorData.get(getLayoutPosition()).getColor());
        }
    }
    //---------

    public interface OnColorChooseListener {
        void onColorChose(int position,String color);
    }

    public void setOnColorChooseListener(OnColorChooseListener onColorChooseListener) {
        this.onColorChooseListener = onColorChooseListener;
    }
}
