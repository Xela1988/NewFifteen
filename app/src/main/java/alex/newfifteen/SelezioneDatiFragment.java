package alex.newfifteen;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Alex on 01/02/2018.
 */
public class SelezioneDatiFragment extends Fragment {
    Context context;
    SaveGame dbSave;
    static final String COL1 = "ID";
    static final String COL2 = "ITEM1";
    final Typeface type = Typeface.DEFAULT;

    ImageView ringraz;

    public SelezioneDatiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//qui va specificato da dove prende l'oggetto da inflatare
        View v = inflater.inflate(R.layout.dialog_layout, container, false);
        Log.d("SelezioneDatiFragment: ", "onCreateView");

        return v;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("SelezioneDatiFragment: ", "onViewCreated");


        // imposto l'id dell'icona che apre i ringraziamenti
        ringraz = (ImageView) view.findViewById(R.id.ringraziamenti);
// gli attribuisco un metodo onClick
        ringraz.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           // qui eseguirà il caricamento dei ringraziamenti
                                           LayoutInflater layoutInflater = LayoutInflater.from(context);
                                           View promptView = layoutInflater.inflate(R.layout.dialog_layout, null);
                                           final AlertDialog dialog = new AlertDialog.Builder(context).create();
                                           // qui imposto view per il "Grazie o Thanksgivings"
                                           TextView teamText = (TextView) promptView.findViewById(R.id.dev01);
                                           TextView teamText2 = (TextView) promptView.findViewById(R.id.roscoProd);
                                           teamText.setTypeface(type);
                                           teamText2.setTypeface(type);

                                           dialog.setView(promptView);

                                           dialog.show();
                                       }
                                   }
        );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        Log.d("SelezioneDatiFragment: ", "onActivityCreated");
    }

    public void invioTag(String tag) {
        Intent intent = new Intent();
        intent.setAction(BroadCastReceiver.Action.ACTION_ROLL_DICE);
        intent.putExtra(BroadCastReceiver.Extras.BUTTOM_TAG, tag);
        LocalBroadcastManager.getInstance(getView().getContext()).sendBroadcast(intent);
    }
}
