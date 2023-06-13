package com.example.fastsms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class SMS extends AppCompatActivity {
    private static final int VR_REQUEST = 999;
    ImageButton sound;
    static String full_name;
    String type;
    static String full_text;
    EditText full;
    Button remember;
    Button deadline;
    Button in_time;
    Button thanks;
    Button files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ImageButton back = findViewById(R.id.imageButton4567);
        back.setOnClickListener(v -> {
            Intent main = new Intent(SMS.this, SendActivity.class);
            startActivity(main);
        });
        TextView namee = findViewById(R.id.textView3);
        namee.setText(SendActivity.name);
        remember = findViewById(R.id.button6);
        deadline = findViewById(R.id.button7);
        in_time = findViewById(R.id.button8);
        thanks = findViewById(R.id.button9);
        files = findViewById(R.id.button10);
        sound = findViewById(R.id.imageButton);
        full = findViewById(R.id.editTextTextPersonName);
        sound.setOnClickListener(v -> {
            listenToSpeech();
        });
        remember.setOnClickListener(v -> {
            type = "remember";
            full_name = full.getText().toString();
            full_text = ", здравствуйте! Не хочется показаться навязчивым, но не могу не отметить, что в последнее " +
                    "время не получал от Вас никакой обратной связи. Пожалуйста, по возможности, свяжитесь со мной в " +
                    "ближайшее время, заранее благодарен.\n" +
                    "С уважением,\n";
            Intent text = new Intent(SMS.this, TextActivity.class);
            startActivity(text);
        });
        deadline.setOnClickListener(v -> {
            type = "deadline";
            full_name = full.getText().toString();
            full_text = ", здравствуйте. Я пишу вам, чтобы напомнить о себе и сообщить, что я заметил, что не укладываюсь в указанный вами срок. Я очень извиняюсь за такую ситуацию и хотел бы выразить свои сожаления.\n" +
                    "Я полностью осознаю важность соблюдения сроков и готов принять любые меры, необходимые для того, чтобы выполнить свои обязательства в срок. Если у вас есть какие-либо конкретные запросы или требования, я буду рад их услышать.\n" +
                    "Еще раз приношу свои извинения и надеюсь на ваше понимание.\n" +
                    "\tС уважением, \n";
            Intent text = new Intent(SMS.this, TextActivity.class);
            startActivity(text);
        });
        in_time.setOnClickListener(v -> {
            type = "in_time";
            full_name = full.getText().toString();
            full_text = ", здравствуйте! Я пишу вам, чтобы напомнить о себе и сообщить, что я уверен, что успею выполнить свои обязательства в указанный вами срок. Я хотел бы убедиться, что вы в курсе процесса и готовы поделиться дополнительными требованиями, если они возникнут. Я ценю ваше время и уважаю необходимость соблюдения сроков. Если у вас есть какие-либо вопросы или замечания, пожалуйста, не стесняйтесь обратиться ко мне.\n" +
                    "\tС уважением, \n";
            Intent text = new Intent(SMS.this, TextActivity.class);
            startActivity(text);
        });
        thanks.setOnClickListener(v -> {
            type = "thanks";
            full_name = full.getText().toString();
            full_text = ", здравствуйте! Я хотел бы выразить свою искреннюю благодарность за вашу помощь. Ваше влияние оказало на меня большое впечатление и помогло достичь значительных результатов.Я надеюсь на возможность продолжать нашу сотрудничество и приложу все усилия, чтобы сохранять такой уровень эффективного и плодотворного общения. Если у вас есть какие-либо идеи или предложения, которые вы бы хотели обсудить, я всегда рад услышать их.\n" +
                    "\tС уважением, \n";
            Intent text = new Intent(SMS.this, TextActivity.class);
            startActivity(text);
        });
        files.setOnClickListener(v -> {
            type = "files";
            full_name = full.getText().toString();
            full_text = ", здравствуйте! Я хотел бы отправить вам несколько файлов. Пожалуйста, обратите внимание на то, что прикрепленные файлы находятся в сжатом формате для вашего удобства. Если у вас возникнут какие-либо вопросы по содержанию файлов, пожалуйста, не стесняйтесь обращаться ко мне. Я всегда готов помочь и предоставить дополнительную информацию.\n" +
                    "C уважением, \n";
            Intent text = new Intent(SMS.this, TextActivity.class);
            startActivity(text);
        });
    }
    private void listenToSpeech() {
        Intent listenIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        listenIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                Objects.requireNonNull(getClass().getPackage()).getName());
        listenIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a word!");
        listenIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        listenIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        startActivityForResult(listenIntent, VR_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VR_REQUEST && resultCode == RESULT_OK) {
            full.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}