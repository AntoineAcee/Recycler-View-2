package com.example.antoinelefevre.recyclerviewtwo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<File> files;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMM yyyy");

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private HashMap<String, ArrayList<File>> filesByMonth = new HashMap<>();
    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Integer> sectionIndexes = new ArrayList<>();

    public FileAdapter(ArrayList<File> files) {
        this.files = files;
        this.sortFilesByMonth();
    }

    private void sortFilesByMonth() {
        for (File file : files) {
            String month = monthFormat.format(file.getCreatedAt());
            ArrayList<File> filesValue = filesByMonth.containsKey(month) ? filesByMonth.get(month) : new ArrayList<File>();
            filesValue.add(file);
            filesByMonth.put(month, filesValue);
        }
        if (files.size() > 0) {
            sectionIndexes.add(0);
        }
        int count = 0;
        for (String key : filesByMonth.keySet()){
            count += filesByMonth.get(key).size()+1;
            sectionIndexes.add(count);
            objects.add(key);
            objects.addAll(filesByMonth.get(key));
        }
        sectionIndexes.remove(sectionIndexes.size()-1);

        System.out.println(filesByMonth);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item, parent, false);
        if (viewType == TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_header, parent, false);
            return new HeaderViewHolder(itemView);
        }
        return new FileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_HEADER) {
            final String month = (String) objects.get(position);

            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.fileHeaderTitle.setText(month);
            return;
        }
        final File file = (File) objects.get(position);

        FileViewHolder fileViewHolder = (FileViewHolder) holder;
        fileViewHolder.fileItemAuthor.setText(file.getAuthor() + " : ");
        fileViewHolder.fileItemName.setText(file.getName());
        fileViewHolder.fileItemDate.setText(dateFormat.format(file.getCreatedAt()));

        int imageType = R.drawable.ic_file;
        switch (file.getType()) {
            case PDF:
                imageType = R.drawable.ic_pdf;
                break;
            case WORD:
                imageType = R.drawable.ic_word;
                break;
            case EXCEL:
                imageType = R.drawable.ic_excel;
                break;
        }
        fileViewHolder.fileItemType.setImageResource(imageType);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return sectionIndexes.contains(position);
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.file_header_title) TextView fileHeaderTitle;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.file_item_author) TextView fileItemAuthor;
        @BindView(R.id.file_item_name) TextView fileItemName;
        @BindView(R.id.file_item_date) TextView fileItemDate;
        @BindView(R.id.file_item_type) ImageView fileItemType;

        public FileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
