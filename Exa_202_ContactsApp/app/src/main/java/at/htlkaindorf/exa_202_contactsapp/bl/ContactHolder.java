package at.htlkaindorf.exa_202_contactsapp.bl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.exa_202_contactsapp.MainActivity;
import at.htlkaindorf.exa_202_contactsapp.ShowContact;
import at.htlkaindorf.exa_202_contactsapp.beans.Contact;

public class ContactHolder extends RecyclerView.ViewHolder {
    private LinearLayout layout;
    private ImageView imgView;
    private TextView tvName;
    private Contact contact;
    private int index;


    @SuppressLint("ClickableViewAccessibility")
    public ContactHolder(@NonNull View itemView, LinearLayout layout, ImageView imgView, TextView tvName) {
        super(itemView);
        this.layout = layout;
        this.imgView = imgView;
        this.tvName = tvName;

        layout.setOnClickListener(v -> {
            Utils.currentContact = contact;
            Utils.currentIndex = index;
            Intent intent = new Intent(Utils.main, ShowContact.class);
            Utils.main.startActivity(intent);
        });

        final GestureDetectorCompat gdc = new GestureDetectorCompat(Utils.main.getApplicationContext(), new SwipeListener());

        layout.setOnTouchListener((v, event) -> gdc.onTouchEvent(event));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    class SwipeListener extends SimpleOnGestureListener {
        private static final int MIN_DIST = 70;
        private static final int MAX_DIST = 1000;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e1.getX() - e2.getX();
            float deltaY = e1.getY() - e2.getY();
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);

            if (MIN_DIST < deltaXAbs && deltaXAbs < MAX_DIST) {
                if (deltaX < 0) {
                    Utils.main.removeContact(contact);
                    return true;
                }
            }
            return false;
        }
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }
}
