package br.com.lFahning.model;

/**
 *
 * @author Rafael Carvalhal
 */
public class Grafico {

    private int idgrafico;
    private String data;
    private String hora;
    private float spm1;
    private float spm2;
    private float spm3;
    private float spm4;
    private float totspm;
    private float totstk;
    private float vazent;
    private float vazsaida;
    private float fluxoret;
    private float voltanque;
    private float pbeng;
    private float pchoke;
    private float pkill;
    private float taxapenet;
    private float torque;

    public Grafico() {
    }

    public int getIdgrafico() {
        return idgrafico;
    }

    public void setIdgrafico(int idgrafico) {
        this.idgrafico = idgrafico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public float getSpm1() {
        return spm1;
    }

    public void setSpm1(float spm1) {
        this.spm1 = spm1;
    }

    public float getSpm2() {
        return spm2;
    }

    public void setSpm2(float spm2) {
        this.spm2 = spm2;
    }

    public float getSpm3() {
        return spm3;
    }

    public void setSpm3(float spm3) {
        this.spm3 = spm3;
    }

    public float getSpm4() {
        return spm4;
    }

    public void setSpm4(float spm4) {
        this.spm4 = spm4;
    }

    public float getTotspm() {
        return totspm;
    }

    public void setTotspm(float totspm) {
        this.totspm = totspm;
    }

    public float getTotstk() {
        return totstk;
    }

    public void setTotstk(float totstk) {
        this.totstk = totstk;
    }

    public float getVazent() {
        return vazent;
    }

    public void setVazent(float vazent) {
        this.vazent = vazent;
    }

    public float getVazsaida() {
        return vazsaida;
    }

    public void setVazsaida(float vazsaida) {
        this.vazsaida = vazsaida;
    }

    public float getFluxoret() {
        return fluxoret;
    }

    public void setFluxoret(float fluxoret) {
        this.fluxoret = fluxoret;
    }

    public float getVoltanque() {
        return voltanque;
    }

    public void setVoltanque(float voltanque) {
        this.voltanque = voltanque;
    }

    public float getPbeng() {
        return pbeng;
    }

    public void setPbeng(float pbeng) {
        this.pbeng = pbeng;
    }

    public float getPchoke() {
        return pchoke;
    }

    public void setPchoke(float pchoke) {
        this.pchoke = pchoke;
    }

    public float getPkill() {
        return pkill;
    }

    public void setPkill(float pkill) {
        this.pkill = pkill;
    }

    public float getTaxapenet() {
        return taxapenet;
    }

    public void setTaxapenet(float taxapenet) {
        this.taxapenet = taxapenet;
    }

    public float getTorque() {
        return torque;
    }

    public void setTorque(float torque) {
        this.torque = torque;
    }

}
