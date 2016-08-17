package paralelo1.poo.sossesolidario.server;

import com.strongloop.android.loopback.ModelRepository;

import paralelo1.poo.sossesolidario.objects.CA;

/**
 * Created by FIMCP on 16/08/2016.
 */
public class CARepo extends ModelRepository<CA> {
    public CARepo() {
        super("CA", CA.class);
    }
}
