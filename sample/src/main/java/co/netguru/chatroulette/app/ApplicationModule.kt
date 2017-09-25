package co.netguru.chatroulette.app

import android.app.Application
import android.content.Context
import android.content.res.Resources
import co.netguru.chatroulette.data.firebase.FirebaseIceCandidates
import co.netguru.chatroulette.data.firebase.FirebaseIceServers
import co.netguru.chatroulette.data.firebase.FirebaseSignalingAnswers
import co.netguru.chatroulette.data.firebase.FirebaseSignalingOffers
import co.netguru.chatroulette.webrtc.service.WebRtcServiceController
import co.netguru.simplewebrtc.WebRtcClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideResources(): Resources = application.resources

    @Provides
    fun provideWebRtcClient(context: Context) = WebRtcClient(context)

    @Provides
    fun provideWebRtcServiceController(webRtcClient: WebRtcClient, firebaseSignalingAnswers: FirebaseSignalingAnswers,
                                       firebaseSignalingOffers: FirebaseSignalingOffers, firebaseIceCandidates: FirebaseIceCandidates,
                                       firebaseIceServers: FirebaseIceServers): WebRtcServiceController {
        return WebRtcServiceController(
                webRtcClient, firebaseSignalingAnswers, firebaseSignalingOffers,
                firebaseIceCandidates, firebaseIceServers)
    }
}