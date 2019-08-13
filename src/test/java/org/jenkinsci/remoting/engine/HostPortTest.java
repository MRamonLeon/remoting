package org.jenkinsci.remoting.engine;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HostPortTest {

    @Test
    public void testHostname() {
        HostPort hostPort = new HostPort("hostname:5555");
        assertThat(hostPort.getHost(), is("hostname"));
        assertThat(hostPort.getPort(), is(5555));
    }

    @Test
    public void testFqdn() {
        HostPort hostPort = new HostPort("hostname.example.com:5555");
        assertThat(hostPort.getHost(), is("hostname.example.com"));
        assertThat(hostPort.getPort(), is(5555));
    }

    @Test
    public void testIPv4() {
        HostPort hostPort = new HostPort("1.2.3.4:5555");
        assertThat(hostPort.getHost(), is("1.2.3.4"));
        assertThat(hostPort.getPort(), is(5555));
    }

    @Test
    public void testIPv6() {
        HostPort hostPort = new HostPort("1:2::3:4:5555");
        assertThat(hostPort.getHost(), is("1:2::3:4"));
        assertThat(hostPort.getPort(), is(5555));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPort() {
        new HostPort("1:2::3:4:host");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoPort() {
        new HostPort("hostname");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoHost() {
        new HostPort(":5555");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyHost() {
        new HostPort("    :5555");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPort() {
        new HostPort("hostname:   ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSeparatorNoPort() {
        new HostPort("hostname:");
    }

}