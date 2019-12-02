package at.htlkaindorf.exa_202_contactsapp.bl;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.exa_202_contactsapp.R;
import at.htlkaindorf.exa_202_contactsapp.beans.Contact;
import at.htlkaindorf.exa_202_contactsapp.beans.IO_Handler;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
    private List<Contact> contacts = new ArrayList<>();
    private List<Contact> filteredContacts = new ArrayList<>();
    private String currentFilter = "";

    public ContactAdapter() {
        List<Contact> temp = IO_Handler.loadContacts();

        contacts.addAll(temp);
        filteredContacts.addAll(temp);
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);

        LinearLayout cLayout = view.findViewById(R.id.cLayout);
        TextView tvName = view.findViewById(R.id.tvName);
        ImageView imgView = view.findViewById(R.id.imgView);

        return new ContactHolder(view, cLayout, imgView, tvName);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact = filteredContacts.get(position);
        int index = contacts.indexOf(contact);
        contact = contacts.get(index);
        holder.setContact(contact);
        holder.setIndex(index);

        Picasso.get().load(contact.getPicture()).placeholder(R.drawable.bg).into(holder.getImgView());
        String name = contact.getLastname() + ", " + contact.getFirstname();
        holder.getTvName().setText(name);
    }

    @Override
    public int getItemCount() {
        return filteredContacts.size();
    }

    public void filterContacts(String filter) {
        filteredContacts.clear();
        filteredContacts.addAll(contacts);
        currentFilter = filter;

        filteredContacts.removeIf(c -> !c.getLastname().toLowerCase().contains(filter.toLowerCase()));

        this.notifyDataSetChanged();
    }

    public void update() {
        if (Utils.currentContact != null) {
            contacts.set(Utils.currentIndex, Utils.currentContact);
        }
        this.filterContacts(currentFilter);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        this.filterContacts(currentFilter);
    }
}
