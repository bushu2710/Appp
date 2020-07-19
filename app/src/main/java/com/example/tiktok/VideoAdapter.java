package com.example.tiktok;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

public class VideoAdapter extends  RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {


    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder videoViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}


    static class VideoViewHolder extends RecyclerView.ViewHolder{
        VideoView videoView;
        TextView textVideoTitle,textDesc;
        ProgressBar videoProgressbar;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView =itemView.findViewById(R.id.videoview);
            textVideoTitle = itemView.findViewById(R.id.videotitle);
            textDesc = itemView.findViewById(R.id.videodesc);
            videoProgressbar = itemView.findViewById(R.id.pb);
        }
        void setVideoData(VideoItem videoItem){
            textVideoTitle.setText(videoItem.videoTitle);
            textDesc.setText(videoItem.videoDesc);
            videoView.setVideoPath(videoItem.videoURL);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    videoProgressbar.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio = mp.getVideoWidth()/(float) mp.getVideoHeight();
                    float screenRatio = videoView.getWidth()/ (float) videoView.getHeight();

                    float scale = videoRatio/screenRatio;
                    if(scale>= 1f){
                        videoView.setScaleX(scale);
                    }else {
                        videoView.setScaleY(1f / scale);
                    }
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }
}
