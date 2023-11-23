package kz.cource.jihcforum.ui.profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kz.cource.jihcforum.EditImageActivity;
import kz.cource.jihcforum.R;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    CircleImageView userPhoto;
    TextView userName, userEmail, userPassword;
    Button editButton;

    private ImageView backgroundImageView;
    private ImageView userPhotoImageView;

    private static final int EDIT_IMAGE_REQUEST = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userPhoto = view.findViewById(R.id.userPhoto);

        backgroundImageView = view.findViewById(R.id.background);
        userPhotoImageView = view.findViewById(R.id.userPhoto);
        editButton = view.findViewById(R.id.editButton);

        // Загрузка изначальных изображений
        backgroundImageView.setImageResource(R.drawable.default_background);
        userPhotoImageView.setImageResource(R.drawable.default_user_photo);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создание интента для запуска активности EditImageActivity
                Intent intent = new Intent(getActivity(), EditImageActivity.class);

                // Передача текущих значений изображений в интент
                intent.putExtra("backgroundResId", R.drawable.default_background);
                intent.putExtra("userPhotoResId", R.drawable.default_user_photo);

                // Запуск активности EditImageActivity
                startActivityForResult(intent, EDIT_IMAGE_REQUEST);
            }
        });


        // Получение данных пользователя из аргументов фрагмента
        Bundle args = getArguments();
        if (args != null) {
            String uName = args.getString("name");
            String uEmail = args.getString("email");
            String uPass = args.getString("password");

            userName = view.findViewById(R.id.userName);
            userEmail = view.findViewById(R.id.userEmail);
            userPassword = view.findViewById(R.id.userPassword);

            userName.setText(uName);
            userEmail.setText(uEmail);
            userPassword.setText("Your password: " + uPass);
        }



        Glide.with(this)
                .load("https://avatars.githubusercontent.com/u/116626295?v=4")
                .centerCrop()
                .placeholder(R.drawable.baseline_account_box_24)
                .into(userPhoto);

        setHasOptionsMenu(true); // Добавляем эту строку, чтобы показать меню во фрагменте

        // Обработка нажатия на кнопку настроек
        ImageView btnSettings = view.findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new LanguageDialogFragment();
                dialog.show(getChildFragmentManager(), "language_dialog");
            }
        });

        return view;
    }

    //for change images
    // Обработка результата из активности редактирования изображений
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            if (data != null) {
                Uri backgroundUri = data.getParcelableExtra("backgroundUri");
                Uri userPhotoUri = data.getParcelableExtra("userPhotoUri");

                if (backgroundUri != null) {
                    backgroundImageView.setImageURI(backgroundUri);
                }
                if (userPhotoUri != null) {
                    userPhotoImageView.setImageURI(userPhotoUri);
                }
            }
        }
    }

    public static class LanguageDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Languages")
                    .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Обработка выбора языка
                            String selectedLanguage = "";
                            switch (which) {
                                case 0:
                                    selectedLanguage = "kk";
                                    break;
                                case 1:
                                    selectedLanguage = "ru";
                                    break;
                                case 3:
                                    selectedLanguage = "en";
                                // Добавьте другие языки по мере необходимости
                            }

                            // Создайте объект Configuration и установите выбранный язык
                            Configuration config = new Configuration();
                            config.setLocale(new Locale(selectedLanguage));

                            // Обновите конфигурацию приложения
                            getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                            // Перезагрузите текущую активность
                            Intent intent = requireActivity().getIntent();
                            requireActivity().finish();
                            startActivity(intent);
                        }
                    });
            return builder.create();
        }
    }
}
