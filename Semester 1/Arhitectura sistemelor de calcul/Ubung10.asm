bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

extern exit,printf ,fopen,fclose,fread,scanf,fprintf             
import exit msvcrt.dll 
import printf msvcrt.dll 
import fopen msvcrt.dll 
import scanf msvcrt.dll 
import fclose msvcrt.dll 
import fread msvcrt.dll 
import fprintf msvcrt.dll
segment data use32 class=data
    ; ...   
    message db "nr= ", 0
    n dd 0
    format_string db "%s", 0
    caracter_spatiu db " ", 0
    format db "%d",0
    format_pozitive db "P: %d",0Ah,0
    format_negative db "N: %d",0Ah,0
    message2 db "Keine Zahlen",0
    format_message2 db "%s",0
    
    numere_pozitive resb 0
    numere_negative resb 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov esi,0
        mov ebx,0
        
        lesen:
            push dword message  ;ich drucke der Text 'nr= '
            call [printf] 
            add esp, 4*1  
            
            push dword n 
            push dword format 
            call [scanf]
            add esp,4*2
            
            mov edx,[n]
            cmp edx,0
            je final_lesen
            
            mov eax,[n]
            mov ecx,-1
            cmp eax,ecx
            jle negativ
            mov edi,1
            cmp eax, edi
            jge pozitiv 
            
            negativ:
                mov [numere_negative+esi],eax
                inc esi
                jmp lesen

    
            pozitiv:
                mov [numere_pozitive+ebx],eax
                inc ebx 
                jmp lesen
              
        ;jmp lesen
        final_lesen:
        
        mov ebx,0
        mov edx,0
        
        
        push dword [numere_negative] 
        push dword format_negative
        call [printf]
        add esp,4*2
                
        
        push dword [numere_pozitive] 
        push dword format_pozitive
        call [printf]
        add esp,4*2
        
        
        
        mov esi,[numere_negative]
        cmp esi,0
        jne endw
        ;we use the function fclose to close the file
        ;fclose(descriptor_fisier)
        push dword message2
        push dword format_message2
        call [printf]
        add esp,4*3
       
        endw:
        
        
        ; Wir lesen von den Tastatur mit der while schleife genannt lesen bis man eine Ziffer 0 hinzukommt 
        ; in eax haben wir die Ziffer getan und man uberpruft ob sie kleiner als -1, dann ist sie eine negative Zahl und wenn nicht uberpruft man 
        ;ob si groser oder gleich mit 1 ist, dann ist sie eine pozitive Zahl 
        ;wir tuen die ziffer in 2 verschiedenen variablen
        ; wenn sie negativ ist dan tut man in numere_negative und wir incrementieren esi, wir tuen die zahlen auf die Pozition esi
        ; bei der negativen ist es das gleiche aber wir incrementiere ebx
        ;wir tuen sie auf den Bildschirm und wenn keine existieren wird auf den Bilschirm 'keine Zahlen' kommen 
        
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
