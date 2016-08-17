package paralelo1.poo.sossesolidario.server;

import com.strongloop.android.loopback.ModelRepository;

import paralelo1.poo.sossesolidario.objects.Necesidad;

/**
 * Created by FIMCP on 16/08/2016.
 */
public class NecesidadRepo extends ModelRepository<Necesidad> {
    public NecesidadRepo() {
        super("necesidad", Necesidad.class);
    }
}
