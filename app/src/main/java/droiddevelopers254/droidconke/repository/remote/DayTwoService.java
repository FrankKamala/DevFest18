package droiddevelopers254.droidconke.repository.remote;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

import droiddevelopers254.droidconke.models.SessionsModel;
import io.reactivex.Single;

public class DayTwoService {

    public DayTwoService(){

    }
    public Single<List<SessionsModel>> getDayOneSessions(){
        return Single.create(e ->{
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            firebaseFirestore.collection("day_two")
                    .orderBy("id", Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()){
                            List<SessionsModel> sessionsModelList=queryDocumentSnapshots.toObjects(SessionsModel.class);
                            e.onSuccess(sessionsModelList);
                        }

                    })
                    .addOnFailureListener(error -> e.onError(new Throwable()));
        });
    }
}
