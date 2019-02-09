package com.example.antoinelefevre.recyclerviewtwo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<File> files;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMM yyyy");

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private HashMap<String, ArrayList<File>> filesByMonth = new HashMap<>();

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
        final File file = files.get(position - getNumberHeaderPast(position));

        if (holder.getItemViewType() == TYPE_HEADER) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.fileHeaderTitle.setText(monthFormat.format(file.getCreatedAt()));
            return;
        }

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
        return files.size() + filesByMonth.keySet().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        ArrayList<Integer> headerPosition = new ArrayList<>();
        headerPosition.add(0);
        for(Map.Entry<String, ArrayList<File>> entry : filesByMonth.entrySet()) {
            headerPosition.add(entry.getValue().size() + 1);
        }
        return headerPosition.contains(position);
    }

    private int getNumberHeaderPast(int position) {
        ArrayList<Integer> headerPositions = new ArrayList<>();
        headerPositions.add(0);
        for(Map.Entry<String, ArrayList<File>> entry : filesByMonth.entrySet()) {
            headerPositions.add(entry.getValue().size() + 1);
        }
        for (int i = 1; i < headerPositions.size(); i++) {
            if (position > headerPositions.get(i-1) && position <= headerPositions.get(i)) {
                return i+1;
            }
        }
        return 0;
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
