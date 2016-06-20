package gr23.sv.ues.fia.asistcompras.Entidades;

import android.content.Context;
import android.content.Intent;

import gr23.sv.ues.fia.asistcompras.R;

/**
 * Created by Moisés on 18/06/2016.
 */
public class Social {
    public static void share(Context ctx, String subject, String text) {
        final Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(Intent.createChooser(intent, ctx.getString(R.string.tit_share)));
    }
}
