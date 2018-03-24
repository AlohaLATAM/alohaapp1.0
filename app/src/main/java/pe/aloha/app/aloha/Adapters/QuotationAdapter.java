package pe.aloha.app.aloha.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import pe.aloha.app.aloha.Core.Utils;
import pe.aloha.app.aloha.Core.model.QuotationResponse;
import pe.aloha.app.aloha.R;
import pe.aloha.app.aloha.UI.QuotationActivity;

/**
 * Created by loualcala on 20/02/18.
 */

public class QuotationAdapter extends RecyclerView.Adapter<QuotationAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<QuotationResponse> quotations;

    public QuotationAdapter(Context context, ArrayList<QuotationResponse> quotations) {
        this.context = context;
        this.quotations = quotations;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_quotation, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        QuotationResponse quotation = quotations.get(position);

        holder.date.setText(Utils.convertDate(quotation.getDatetime_of_service()));
        holder.from.setText(quotation.getAddress_from());
        holder.to.setText(quotation.getAddress_to());
        holder.price.setText(quotation.getTotal_price());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuotationActivity.class);
                intent.putExtra("quotation_id", quotations.get(position).getId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int i) { return 0; }

    @Override
    public int getItemCount() {
        return quotations.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.list_item_quotation);
            date = itemView.findViewById(R.id.quotation_value_date);
            from = itemView.findViewById(R.id.quotation_value_from);
            to = itemView.findViewById(R.id.quotation_value_to);
            price = itemView.findViewById(R.id.quotation_label_price);
        }

        ConstraintLayout container;
        TextView date;
        TextView from;
        TextView to;
        TextView price;
    }
}
