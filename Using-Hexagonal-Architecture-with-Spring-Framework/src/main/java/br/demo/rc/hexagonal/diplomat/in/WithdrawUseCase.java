package br.demo.rc.hexagonal.diplomat.in;

public interface WithdrawUseCase {
    boolean withdraw(Long id, Float amount);
}
