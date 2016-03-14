package net.sourceforge.peers.demo;

import java.net.SocketException;

import net.sourceforge.peers.Config;
import net.sourceforge.peers.FileLogger;
import net.sourceforge.peers.Logger;
import net.sourceforge.peers.javaxsound.JavaxSoundManager;
import net.sourceforge.peers.sdp.Codec;
import net.sourceforge.peers.sip.core.useragent.SipListener;
import net.sourceforge.peers.sip.core.useragent.UserAgent;
import net.sourceforge.peers.sip.syntaxencoding.SipUriSyntaxException;
import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;

public class EventManager implements SipListener {

    private UserAgent userAgent;
    private SipRequest sipRequest;
    private CommandsReader commandsReader;

    public EventManager() throws SocketException {
        Config config = new CustomConfig();
        Logger logger = new FileLogger(null);
        JavaxSoundManager javaxSoundManager = new JavaxSoundManager(false, logger, null);
        userAgent = new UserAgent(this, config, logger, javaxSoundManager);
        new Thread() {
            public void run() {
                try {
                    userAgent.register();
                } catch (SipUriSyntaxException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        commandsReader = new CommandsReader(this);
        commandsReader.start();
    }


    // commands methods
    public void call(final String callee) {
        new Thread() {
            @Override
            public void run() {
                try {
                    sipRequest = userAgent.invite(callee, null);
                } catch (SipUriSyntaxException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void hangup() {
        new Thread() {
            @Override
            public void run() {
                userAgent.terminate(sipRequest);
            }
        }.start();
    }


    public static void main(String[] args) {
        try {
            new EventManager();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void onRegistering(SipRequest sipRequest) {

    }

    public void onRegisterSuccessful(SipResponse sipResponse) {

    }

    public void onRegisterFailed(SipResponse sipResponse) {

    }

    public void onIncomingCall(SipRequest sipRequest, SipResponse provResponse) {

    }

    public void onRemoteHangup(SipRequest sipRequest) {

    }

    public void onRinging(SipResponse sipResponse) {

    }

    public void onCalleePickup(SipResponse sipResponse) {

    }

    public void onError(SipResponse sipResponse) {

    }

    public void onMediaSessionStarted(UserAgent ua, Codec codec) {

    }

    public void onMediaSessionStopped(UserAgent ua) {

    }
}
