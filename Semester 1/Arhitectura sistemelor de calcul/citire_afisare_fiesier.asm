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
    file_name db "Prufung712_1.txt",0
    file_name_output db "output_1.txt",0
    acces_mode db "r",0
    acces_mode_write db "w",0
    file_descriptor dd -1
    file_descriptor_output dd -1
    len equ 100
    buffer resb len
    text times 200 db 0
    
    format_old db "old Text: %s", 0Ah,0
    format db "New text: %s", 0Ah,0
    word1 times 401 db 0
    

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        push dword acces_mode
        push dword file_name
        call [fopen]
        add esp, 4*2
        
        cmp eax,0
        je final 
        mov [file_descriptor],eax
        
        mov edx,0
        mov edi,0
        mov esi,0
        
        citire:
            push dword [file_descriptor]
            push dword len
            push dword 1
            push dword buffer
            call [fread] ;citeste fiecare caracter 
            add esp,4*4
            
            cmp eax,0
            je sfarsit_citire
            
            mov eax,0
            mov al,[buffer]
            mov [text+esi],al
            inc esi
            
            cmp al,'0'
            jl nu_numar
            cmp al,'9'
            jg nu_numar
            mov [word1+edi],al
            inc edi
            jmp citire
            nu_numar:
                cmp al,'a'
                jl nu_litera_mica
                cmp al,'z'
                jg nu_litera_mica
                mov [word1+edi],al
                inc edi
                jmp citire 
                
            nu_litera_mica:
                cmp al,'A'
                jl nu_litera_mare
                cmp al,'Z'
                jg nu_litera_mare
                mov [word1+edi],al
                inc edi 
                jmp citire 
            nu_litera_mare:
                ;daca ajunmge aici inseamna ca e caracter 
                mov [word1+edi],al
                inc edi
                mov [word1+edi],al
                inc edi
                jmp citire
        sfarsit_citire:
        push dword [file_descriptor]
        call [fclose]
        add esp,4
        
        push dword acces_mode_write
        push dword file_name_output
        call [fopen]
        add esp,4*2
        
        mov [file_descriptor_output],eax
       
        
        push dword text
        push dword format_old
        push dword [file_descriptor_output]
        call [fprintf]
        add esp,4*3
        
        push dword word1
        push dword format
        push dword [file_descriptor_output]
        call [fprintf]
        add esp,4*3
        
        push dword [file_descriptor_output]
        call [fclose]
        add esp,4
        
        final:
        
        
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
